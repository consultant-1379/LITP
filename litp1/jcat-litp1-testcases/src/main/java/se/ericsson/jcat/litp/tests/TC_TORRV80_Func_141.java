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

/**
 * Use DataProvider to pass managed nodes to check their status
 * 
 * @author esensen
 * 
 */
public class TC_TORRV80_Func_141 extends TC_TORRV_Func {

	/**
	 * @param connect
	 */
	public TC_TORRV80_Func_141(SSHConnect connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}

	@Test(dataProvider = "nodes")
	public void logonSC(String node) {
		System.out.println("Testing node: " + node);

		setTestcase("FilesystemShares" + connect.getServer(),
				"Number of Filesystems mounted on " + node);
		String result = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'showmount -e nfs-1| wc -l'");
		setTestStep("No of Filesystem Shares");
		setTestInfo(result);

		setTestcase("FilesystemList: " + node + ": " + connect.getServer(),
				"List of filesystem mounted");
		String result1 = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'showmount -e nfs-1'");
		setTestStep("List of Filesystems");
		setTestInfo(result1);

	}

}
