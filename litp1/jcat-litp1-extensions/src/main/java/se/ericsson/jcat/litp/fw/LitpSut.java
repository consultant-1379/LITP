package se.ericsson.jcat.litp.fw;

import se.ericsson.jcat.fw.SUT;

/**
 * 
 * @author uabcraw
 * 
 *         A concrete implementation of SUT Used when testcases are to
 *         communicate with a SUT
 * 
 */
public class LitpSut extends SUT {

	public LitpSut() {
	}

	public LitpSut(String name) {
		super(name);
	}

	public String fetchLogs(String logfilePath) {
		// implement something to fetch a bunch of logs from the SUT
		// fetchlogs is called when a testcase fails.
		// the logs should include enough information to attach this
		// logfile to a Trouble report
		return "aLogFile.tar.gz";
	}

}
