package com.googlecode.jpingy;

import com.googlecode.jpingy.Ping.Backend;

public class SampleCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PingArguments arguments = new PingArguments("google.com", 1, 5000, 32);

		PingResult results = Ping.ping(arguments, Backend.UNIX);

		System.out.println(results);
	}

}
