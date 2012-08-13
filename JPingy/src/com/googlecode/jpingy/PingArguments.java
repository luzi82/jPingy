package com.googlecode.jpingy;
public class PingArguments {
	String url;
	int repeat;
	long timeout;
	int payload_bytes;

	// TODO replace by builder pattern
	public PingArguments(String url, int repeat, long timeout, int payload_bytes) {
		this.url = url;
		this.repeat = repeat;
		this.timeout = timeout;
		this.payload_bytes = payload_bytes;
	}

	public String getCommand() {
		StringBuilder b = new StringBuilder();

		b.append("ping").append(" ").append("-c").append(" ").append(repeat)
				.append("")

				.append("-W").append("").append(timeout).append(" ")

				.append("-s").append(" ").append(payload_bytes).append(" ")
				.append(url);

		return b.toString();
	}
}