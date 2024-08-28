package se.ericsson.jcat.litp.listeners;

import org.apache.log4j.Logger;
import org.testng.ISuite;

import se.ericsson.jcat.litp.fw.LitpSut;
import se.ericsson.jcat.litp.fw.LitpSutFetchLogs;
import se.ericsson.jcat.litp.utils.VerySimpleServer;
import se.ericsson.jcat.fw.SUTHolder;
import se.ericsson.jcat.fw.ng.listeners.JcatSuiteListener;
import se.ericsson.jcat.fw.utils.TestInfo;

public class LitpSuiteListener extends JcatSuiteListener {
	private Logger logger = Logger.getLogger(this.getClass());
	private static boolean isfetchlog = false;

	@Override
	public void onStart(ISuite arg0) {
		LitpSut[] m = new LitpSut[1];
		m[0] = new LitpSut();
		SUTHolder.getInstance().zones = m;
		super.onStart(arg0);
		logger.info("Setup before the suite");

		// start the Simple server to act as a dummy event
		// generator
		logger.info("Starting dummy server");
		int port = 54321;
		VerySimpleServer server = new VerySimpleServer(port);
		server.start();
		logger.info("Started dummy server");

		// Create a new sute instance which will be held in
		// SUTHolder
		// This sut instance will be available in all testcases
		// so it's possible to target testcases against multiple
		// SUTs

		TestInfo.setDbProperties("logdb.properties");
		TestInfo.setGroupId("demo");
		TestInfo.setSutVersionMajor("LSV1");
		TestInfo.setSutVersionMinor("PA1");
		TestInfo.setFetchLogs("yes");
		if (!isfetchlog) {
			addFetchLogsObject(new LitpSutFetchLogs());
			isfetchlog = true;
		}
	}

	@Override
	public void onFinish(ISuite arg0) {
		logger.info("Teardown after the suite");
		super.onFinish(arg0);
	}
}
