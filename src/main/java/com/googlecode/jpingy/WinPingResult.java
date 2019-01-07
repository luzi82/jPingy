/*
 Copyright (c) 2012 Thomas Goossens

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package tk.ddvudo.ssrdetection.Utils.netHadler.jPingy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tk.ddvudo.ssrdetection.Utils.netHadler.jPingy.PingRequest.PingRequestBuilder;

/**
 * 
 * @author Thomas Goossens
 * @version 0.1a
 * 
 */
public class WinPingResult extends PingResult {

	public WinPingResult(List<String> pingOutput) {
		super(pingOutput);

	}

	private String[] pack;

	private void generatePackageArray(List<String> lines) {
		String packages = null;
		if (pack == null) {
			if (lines.size() - 3 > 0) {
				packages = lines.get(lines.size() - 3);
			}
			pack = packages.split("，");
		}
	}

	@Override
	public int matchTransmitted(List<String> lines) {
		generatePackageArray(lines);
		return Integer.parseInt(pack[0].replaceAll("\\D+", ""));

	}

	@Override
	public int matchReceived(List<String> lines) {
		generatePackageArray(lines);
		return Integer.parseInt(pack[1].replaceAll("\\D+", ""));

	}

	@Override
	public int matchTime(List<String> lines) {
//		generatePackageArray(lines);
//		return Integer.parseInt(pack[3].replaceAll("\\D+", ""));
		return -1;
	}

	private void generateRttArray(List<String> lines) {
		if (rtt == null) {
			// rtt
			String rtts = lines.get(lines.size() - 1);
			String[] rtt_equals = rtts.trim().split("=");
			ArrayList<String> win_rtt = new ArrayList<>();
			for (String s : rtt_equals) {
				if (s.indexOf("ms") > -1) {
					if (s.indexOf("，") > -1) {
						win_rtt.add(s.split("，")[0].replaceAll("ms", ""));
					} else {
						win_rtt.add(s.replaceAll("ms", ""));
					}
				}
			}
			rtt = win_rtt.toArray(new String[win_rtt.size()]);
		}
	}

	private String[] rtt;

	@Override
	public float matchRttMin(List<String> lines) {
		generateRttArray(lines);
		return Float.parseFloat(rtt[0]);

	}

	@Override
	public float matchRttAvg(List<String> lines) {
		generateRttArray(lines);
		return Float.parseFloat(rtt[1]);
	}

	@Override
	public float matchRttMax(List<String> lines) {
		generateRttArray(lines);
		return Float.parseFloat(rtt[2]);
	}

	@Override
	public float matchRttMdev(List<String> lines) {
//		generateRttArray(lines);
//		return Float.parseFloat(rtt[3].replaceAll("\\D+", ""));
		return -1;
	}

	@Override
	public String matchIP(List<String> lines) {
		String str = lines.toString();
		String pattern = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		m.find();

		return m.toMatchResult().group(0);
	}

	@Override
	public int matchTTL(List<String> lines) {
		String str = lines.toString();
		Pattern pattern = Pattern.compile("TTL=([0-9\\.]+)"); // match
		// ttl=decimal

		Matcher matcher = pattern.matcher(str.toString());

		matcher.find();
		MatchResult result = matcher.toMatchResult();

		return Integer.parseInt(result.group(1).replaceAll("TTL=", ""));
	}

	@Override
	protected int parsePayload(List<String> lines) {
		// TODO Auto-generated method stub

		return Integer
				.parseInt(lines.get(1).substring(lines.get(1).indexOf("具有") + 2, lines.get(1).indexOf("字节")).trim());
	}

	@Override
	public List<PingRequest> getRequests() {

		List<PingRequest> requests = new ArrayList<PingRequest>();
		for (String line : getLines()) {
			if (isPingRequest(line)) {
				PingRequest request = createPingRequest(line);
				requests.add(request);
			}
		}
		return requests;

	}

	private PingRequest createPingRequest(String line) {
		String[] split = line.split(" ");
		PingRequestBuilder builder = PingRequest.builder();

		int bytes = Integer.parseInt(split[3].replaceAll("字节=", ""));
		String from = split[1].trim();
		String fromIP = split[1].trim();
		int reqnr = Integer.parseInt(split[3].split("=")[1]);
		int ttl = Integer.parseInt(split[5].split("=")[1]);
		float time = Float.parseFloat(split[4].split("=")[1].replaceAll("ms", ""));

		builder = builder.bytes(bytes).from(from).fromIP(fromIP).reqNr(reqnr).ttl(ttl).time(time);

		return builder.build();

	}

	private boolean isPingRequest(String line) {
		return line.contains("的回复");
	}

}