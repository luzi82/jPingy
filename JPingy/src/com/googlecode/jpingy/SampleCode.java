/*
 Copyright (c) 2012 Thomas Goossens

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.googlecode.jpingy;

import com.googlecode.jpingy.Ping.Backend;

/**
 * 
 * @author Thomas Goossens
 * @version 0.1a
 * 
 */
public class SampleCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PingArguments arguments = new PingArguments.Builder().url("google.com")
				.timeout(5000).repeat(2).bytes(32).build();

		PingResult results = Ping.ping(arguments, Backend.UNIX);

		System.out.println("TTL: " + results.ttl);

		System.out.println("RTT Minimum: " + results.rtt_min);

		System.out.println("Received : " + results.received);
	}

}
