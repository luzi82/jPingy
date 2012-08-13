package com.googlecode.jpingy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
