package com.googlecode.jpingy;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PingResult {
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

	@Override
	public String toString() {
		return "PingResult [address=" + address + ", transmitted="
				+ transmitted + ", ttl=" + ttl + ", time=" + time
				+ ", received=" + received + ", payload=" + payload
				+ ", rtt_min=" + rtt_min + ", rtt_avg=" + rtt_avg
				+ ", rtt_max=" + rtt_max + ", rtt_mdev=" + rtt_mdev + "]";
	}
}