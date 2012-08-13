/*
 Copyright (c) 2012 Thomas Goossens

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.googlecode.jpingy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Thomas Goossens
 * @version 0.1a
 * 
 */
public class Ping {

	public enum Backend {
		UNIX {

			@Override
			public PingResult getResult(List<String> output) {
				return new UnixPingResult(output);
			}

		};

		public abstract PingResult getResult(List<String> output);
	}

	public static PingResult ping(PingArguments ping, Backend backend) {
		try {

			Process p;

			p = Runtime.getRuntime().exec(ping.getCommand());

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String s;

			List<String> lines = new ArrayList<String>();
			while ((s = stdInput.readLine()) != null) {

				lines.add(s);
			}

			p.destroy();
			return backend.getResult(lines);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
