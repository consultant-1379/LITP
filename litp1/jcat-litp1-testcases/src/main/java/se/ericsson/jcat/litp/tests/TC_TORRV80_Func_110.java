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

import se.ericsson.jcat.litp.fw.LitpJcatNgTestCase;
import se.ericsson.jcat.litp.utils.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


//Author: Shen, use for single & multi node dependency test
public class TC_TORRV80_Func_110 extends LitpJcatNgTestCase {
	
	private SSHConnect connect = null;
	private static String name = null;
	
	public TC_TORRV80_Func_110(String name) {
		super(name);
	}

	/**
	 * @param sshConnect
	 */
	public TC_TORRV80_Func_110(SSHConnect connect) {
		super(name);
		this.connect = connect;
		
	}

	@BeforeClass
	public void setUp() throws Exception {
//		this.setTestStep("Testing if host is reachable");
//		//get an instance of Property
//		PropLibrary.getInstance();
//		//initialise SSHConnect
//		connect = new SSHConnect(PropLibrary.sshHost,PropLibrary.sshUser,PropLibrary.sshPass);
//		//check if the host is reachable
//		boolean isReachable = NetworkUtil.hostIsReachable(PropLibrary.sshHost);
//		if (!isReachable) {
//			fail("The SUT can not be reached. Failing the test case");
//		}
	}
	@BeforeMethod
	public void sshConnection() throws InterruptedException{
		
		connect.connect();
	
	//quiting created ssh connection after every method
	}
	@AfterMethod
	public void sshConnectionClose(){
		
		connect.disconnect();
	}

	@Test(groups={"init"})
	public void ping() throws Exception
	{
		setTestcase("Ping to " + connect.getServer(), "check if the host is reachable");
		this.setTestStep("Testing if host is reachable");
		boolean isReachable = NetworkUtil.hostIsReachable(connect.getServer());
		if (!isReachable) {
			fail("The SUT can not be reached. Failing the test case");
			}
	}

	@Test(dependsOnGroups = {"init"})
	public void isSingleNode() {

		setTestcase("Blade Type Single" + connect.getServer(), "Checking the Blade Type");
		setTestStep("Connecting to the " + connect.getServer());
		setTestInfo("Using this username: " + connect.getUser());
		setTestInfo("Using this password: " + connect.getPassword());

		String result = connect.getSsh().sendSshCommand("cat /etc/hosts |grep -i nfs");

		setTestStep("Hosts NFS Info");
		setTestInfo(result);
		boolean isSingle = result.contains("NFS");
		//set Blade Type, not used in this test, may be used later
		if(isSingle)
		{	
			connect.setType(BladeType.SINGLE);
		}
		saveAssertTrue("Blade is Multi-Nodes", isSingle);
		
	}
	//test to check if the blade is multi node.
	@Test
	public void isMultiNode() {

		setTestcase("Blade Type Multi"+connect.getServer(), "Checking the Blade Type");
		setTestStep("Connecting to the " + connect.getServer());
		setTestInfo("Using this username: " + connect.getUser());
		setTestInfo("Using this password: " + connect.getPassword());

		String result = connect.getSsh().sendSshCommand("cat /etc/hosts |grep -i nfs");

		setTestStep("Hosts NFS Info");
		setTestInfo(result);
		boolean isMulti = !result.contains("NFS");
		//set Blade Type
		if(isMulti)
		{	
			connect.setType(BladeType.MULTI);
		}
		saveAssertTrue("Blade is Single-Nodes", isMulti);
	}
	
	//This method is for single node test only
	@Test(dependsOnMethods = {"isSingleNode","ping"})
	public void virshList() {


//		setTestcase("ManagedNodesCount" + connect.getServer(), "No of Virtual Machines");
//		String number = SSHConnect.getSsh().sendSshCommand("virsh list|grep -i vm_site|wc -l");
//		setTestStep("No of Virtual Machines");
//		setTestInfo(number);
//		
		setTestcase("ManagedNodes"+ connect.getServer(), "List of Virtual Machines");
		String nodes = connect.getSsh().sendSshCommand("virsh list");
		setTestStep("List of Managed Nodes");
		setTestInfo(nodes);
		
		saveAssertTrue("There are no Managed Nodes", nodes.contains("vm"));
		
	}
}
