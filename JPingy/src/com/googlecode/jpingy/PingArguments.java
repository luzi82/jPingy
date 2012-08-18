/*
 Copyright (c) 2012 Thomas Goossens

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.googlecode.jpingy;

public class PingArguments {
	String url;
	int count;
	long timeout;
	int payload_bytes;
	long interval;
	int ttl;

	public PingArguments() {

	}

	// TODO make that it can be build

	public static class Builder {

		private PingArguments arguments;

		public Builder() {
			this.arguments = new PingArguments();
		}

		public Builder url(String url) {
			arguments.url = url;
			return this;
		}

		public Builder count(int count) {
			arguments.count = count;
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

		public Builder ttl(int ttl) {
			arguments.ttl = ttl;
			return this;
		}

		/**
		 * -i
		 * 
		 * @param interval
		 * @return
		 */
		public Builder interval(long interval) {
			arguments.interval = interval;
			return this;
		}

		public PingArguments build() {
			return arguments;
		}
	}

	// TODO make adaptable if certain properaties aren't set
	public String getCommand() {
		StringBuilder b = new StringBuilder();

		b.append("ping").append(" ").append("-c").append(" ").append(count)
				.append("")

				.append("-W").append("").append(timeout).append(" ")

				.append("-s").append(" ").append(payload_bytes).append(" ")
				.append(url);

		return b.toString();
	}
}