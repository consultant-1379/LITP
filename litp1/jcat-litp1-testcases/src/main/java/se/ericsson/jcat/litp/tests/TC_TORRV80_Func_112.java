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

public class TC_TORRV80_Func_112 extends TC_TORRV_Func {

	/**
	 * @param connect
	 */
	public TC_TORRV80_Func_112(SSHConnect connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}

	
	@Test(dependsOnGroups={"multi"})
	public void san_storage_components_list() {
		setTestcase("san_storage_components" + "--" + connect.getServer(),
				"test functionality of SAN storage component");
		String cmd = connect.getSsh().
				sendSshCommand("python /opt/ericsson/nms/litp/lib/litp_storage_clariion/storage_management.py --option list");
		boolean result = cmd.contains("0, 1, 2, 3, 4, 5, 6, 7, 8," +
				"9, 10, 11, 12, 187, 188, 2046");
		saveAssertFalse("storage management doesn't list LUNs properly",
				!result);
	}
	
	@Test(dependsOnGroups={"multi"})
	public void san_storage_components_desc() {
		setTestcase("san_storage_components" + "--" + connect.getServer(),
				"test functionality of SAN storage component");
		String cmd = connect.getSsh().
				sendSshCommand("python /opt/ericsson/nms/litp/lib/litp_storage_clariion/storage_management.py --option desc");
//		boolean result = cmd.contains("0, 1, 2, 3, 4, 5, 6, 7, 8," +
//				"9, 10, 11, 12, 187, 188, 2046");
//		saveAssertFalse("storage management doesn't list LUNs properly",
//				!result);
	}
	
	
}
