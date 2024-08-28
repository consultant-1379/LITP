/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package se.ericsson.jcat.litp.tests;

import org.testng.annotations.Test;

import se.ericsson.jcat.litp.fw.TC_TORRV_Func;
import se.ericsson.jcat.litp.utils.SSHConnect;

public class TC_TORRV80_Func_115 extends TC_TORRV_Func {

	/**
	 * @param name
	 */
	public TC_TORRV80_Func_115(String name) {
		super(name);
	}

	/**
	 * @param connect
	 */
	public TC_TORRV80_Func_115(SSHConnect connect) {
		super(connect);
	}

	/**
	 * Send command to lock all managed nodes from SC-1
	 * 
	 * @param node
	 */
	@Test(dataProvider = "nodes")
	public void lockNodes(String node) {
		setTestcase("TC115: Lock:" + node + "--" + connect.getServer(),
				"Lock nodes");
		connect.getSsh().sendSshCommand("ssh SC-1 'cmw-node-lock' " + node);
	}

	/**
	 * verify all the managed nodes are locked
	 */
	@Test(dependsOnMethods = { "lockNodes" })
	public void verifyLockingStatus() {
		String node = "SC-1";
		setTestcase(
				"TC115: Verify nodes locking status--"
						+ connect.getServer(), "Verify nodes locking status");

		String result = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'cmw-status -v node | grep -i unlocked'");
		setTestStep("Verify all nodes are locked");
		setTestInfo(result);

		saveAssertTrue("Locking on CMW is not working", result == null);
	}

	/**
	 * unlocking all managed nodes from sc-1
	 * 
	 * @param node
	 */
	@Test(dataProvider = "nodes", dependsOnMethods = { "lockNodes",
			"verifyLockingStatus" })
	public void unlockNodes(String node) {
		setTestcase("TC115: Unlock:" + node + "--" + connect.getServer(),
				"Unlock nodes");
		connect.getSsh().sendSshCommand("ssh SC-1 'cmw-node-unlock' " + node);
	}

	/**
	 * verify all the managed nodes are unlocked
	 */
	@Test(dependsOnMethods = { "lockNodes", "verifyLockingStatus",
			"unlockNodes" })
	public void verifyUnLockingStatus() {
		// only need to verify one managed node, because it's the same to check
		// nodes status on different managed nodes
		String node = "SC-1";
		setTestcase(
				"TC115: Verify nodes unlocking status--"
						+ connect.getServer(), "Verify nodes unlocking status");
		String result = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'cmw-status -v node | grep -i =locked'");
		setTestStep("Verify all nodes are unlocked");
		setTestInfo(result);

		saveAssertTrue("Unlocking on CMW is not working", result == null);
	}

}
