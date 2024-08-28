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
public class TC_TORRV80_Func_114 extends TC_TORRV_Func {

	/**
	 * @param name
	 */
	public TC_TORRV80_Func_114(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param connect
	 */
	public TC_TORRV80_Func_114(SSHConnect connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}
	
	@Test(dataProvider = "nodes")
	public void tipcStatus(String node) {
		setTestcase("tipcStatus", "Checking veracity of TIPC status");
		String[] nodes = node.split("-");
		String nd = nodes[0].concat(nodes[1]).toLowerCase();
		String result = connect.getSsh().sendSshCommand("litp /inventory/site1/cluster1/" + nd + "/lde/tipc show | grep -i status");
		setTestStep("TIPC address status");
		setTestInfo(result);
		saveAssertTrue("TIPC address status is not correct", result.contains("Acknowledged"));
	}

	@Test(dataProvider = "nodes")
	public void tipcAddressAddress(String node) {
		setTestcase("tipcAddress", "Checking veracity of TIPC address");
		String[] nodes = node.split("-");
		String nd = nodes[0].concat(nodes[1]).toLowerCase();
		String result = connect.getSsh().sendSshCommand("litp /inventory/site1/cluster1/"+ nd + "/lde/tipc show | grep -i address");
		setTestStep("TIPC address");
		setTestInfo(result);
		saveAssertTrue("TIPC address status is not correct", result.contains("1.1."+nodes[1]));
	}

}
