/*
 Copyright (c) 2012 Thomas Goossens

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.googlecode.jpingy;

import java.util.List;

/**
 * 
 * @author Thomas Goossens
 * @version 0.1a
 * 
 */
public abstract class PingResult {

	private List<String> lines;

	public final String address;
	public final int transmitted;
	public final int ttl;
	public final long time;
	public final int received;
	public final int payload;
	public final float rtt_min;
	public final float rtt_avg;
	public final float rtt_max;
	public final float rtt_mdev;

	protected PingResult(List<String> pingOutput) {

		this.lines = pingOutput;
		transmitted = matchTransmitted(pingOutput);
		received = matchReceived(pingOutput);
		time = matchTime(pingOutput);

		rtt_min = matchRttMin(pingOutput);
		rtt_avg = matchRttAvg(pingOutput);
		rtt_max = matchRttMax(pingOutput);
		rtt_mdev = matchRttMdev(pingOutput);

		ttl = matchTTL(pingOutput);

		address = matchIP(pingOutput);

		payload = parsePayload(pingOutput);

	}

	public List<String> getLines() {
		return lines;
	}

	protected abstract int parsePayload(List<String> lines);

	protected abstract int matchTransmitted(List<String> lines);

	protected abstract int matchReceived(List<String> lines);

	protected abstract int matchTime(List<String> lines);

	protected abstract float matchRttMin(List<String> lines);

	protected abstract float matchRttAvg(List<String> lines);

	protected abstract float matchRttMax(List<String> lines);

	protected abstract float matchRttMdev(List<String> lines);

	protected abstract String matchIP(List<String> lines);

	protected abstract int matchTTL(List<String> lines);

	public abstract List<PingRequest> getRequests();

	@Override
	public String toString() {
		return "PingResult [address=" + address + ", transmitted="
				+ transmitted + ", ttl=" + ttl + ", time=" + time
				+ ", received=" + received + ", payload=" + payload
				+ ", rtt_min=" + rtt_min + ", rtt_avg=" + rtt_avg
				+ ", rtt_max=" + rtt_max + ", rtt_mdev=" + rtt_mdev + "]";
	}
}