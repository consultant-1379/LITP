package se.ericsson.jcat.litp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class NetworkUtil {

	private static Logger logger = Logger
			.getLogger(NetworkUtil.class.getName());

	/**
	 * Tests if a ip address is reachable with Ping.
	 * 
	 * @param hostname
	 *            the ip to ping
	 * @return true if host is reachable
	 */
	public static boolean hostIsReachable(String hostname) {

		logger.info("Testing if host " + hostname + " is reachable");
		boolean isReachable = false;

		Process pingProc = null;
		try {
	        String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                // use this line for windows
                pingProc = Runtime.getRuntime().exec("ping -n 2 " + hostname);
            } else {
                // use this line for linux
                pingProc = Runtime.getRuntime().exec("ping -c 2 " + hostname);
            }
	
            InputStream iS = pingProc.getInputStream();
			BufferedReader bR = new BufferedReader(new InputStreamReader(iS));
			String pingResponse;
			while ((pingResponse = bR.readLine()) != null) {
				logger.info(pingResponse);
				if (pingResponse.contains("from " + hostname)
						&& !pingResponse.contains("host unreachable")) {
					isReachable = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("The host " + hostname + " was reachable = " + isReachable);

		return isReachable;

	}

}
