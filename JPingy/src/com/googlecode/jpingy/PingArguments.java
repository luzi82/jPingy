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

	public PingArguments() {
		// TODO Auto-generated constructor stub
	}

	public static class Builder {

		private PingArguments arguments;

		public Builder() {
			this.arguments = new PingArguments();
		}

		public Builder url(String url) {
			arguments.url = url;
			return this;
		}

		public Builder repeat(int repeat) {
			arguments.repeat = repeat;
			return this;
		}

		public Builder timeout(long timeout) {
			arguments.timeout = timeout;
			return this;
		}

		public Builder bytes(int bytes) {
			arguments.payload_bytes = bytes;
			return this;
		}

		public PingArguments build() {
			return arguments;
		}
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