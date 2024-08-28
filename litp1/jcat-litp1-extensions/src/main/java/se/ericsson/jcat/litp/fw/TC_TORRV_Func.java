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
package se.ericsson.jcat.litp.fw;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.*;

import se.ericsson.jcat.litp.fw.LitpJcatNgTestCase;
import se.ericsson.jcat.litp.utils.*;
/**
 * Super class for all TORRV functional test cases
 * @author esensen
 *
 */
//@Test(singleThreaded=true)
public class TC_TORRV_Func extends LitpJcatNgTestCase {
	
	/**
	 * param definition
	 */
	protected SSHConnect connect = null;
	private static String name = null;
	private static SSHTool tool = null;

	/** 
	 * @param name
	 */
	public TC_TORRV_Func(String name) {
		super(name);
		TC_TORRV_Func.setName(name);
	}
	
	public TC_TORRV_Func(SSHConnect connect)
	{
		super(getName());
		this.connect = connect;
		tool = new SSHTool(connect);
	}

	/**
	 * @return the name
	 */
	public static String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public static void setName(String name) {
		TC_TORRV_Func.name = name;
	}
	
	/**
	 * @return the connect
	 */
	public SSHConnect getConnect() {
		return connect;
	}

	/**
	 * @param connect the connect to set
	 */
	public void setConnect(SSHConnect connect) {
		this.connect = connect;
	}

	@BeforeClass
	protected void setUp() throws Exception
	{
		this.setTestStep("Testing if host is reachable");
		//check if the host is reachable
		boolean isReachable = NetworkUtil.hostIsReachable(connect.getServer());
		if (!isReachable) {
			fail("The SUT can not be reached. Failing the test case");
		}
	}
	
	@BeforeMethod
	protected  void sshConnection(){
		connect.connect();
	//quiting created ssh connection after every method
	}
	@AfterMethod
	protected  void sshConnectionClose(){
		
		connect.disconnect();
	}
	
	
//	/**
//	 * Inner class for dataProvider
//	 * @author esensen
//	 *
//	 */
//	public static class StaticDataProvider {
//		
//		public StaticDataProvider()
//		{}
//		
////		@DataProvider(name="nodes")
////		public Iterator<Object[]> getManagedNodes()
////		{
////			String[] nodes = tool.getNodes();
////			ArrayList<Object[]> dataReturned = new ArrayList<Object[]>();
////			
////			for(String node:nodes)
////			{
////				dataReturned.add(new Object[]{node});
////			}
////			
////			return dataReturned.iterator();
////		}
//
//	}

	/**
	 * DataProvider Method to get all managed nodes
	 * Use an Iterator to lazy load the managed nodes
	 * @return Iterator of managed nodes
	 */
	@DataProvider(name="nodes")
	public Iterator<Object[]> getManagedNodes()
	{
		String[] nodes = tool.getNodes();
		ArrayList<Object[]> dataReturned = new ArrayList<Object[]>();
		
		for(String node:nodes)
		{
			dataReturned.add(new Object[]{node});
		}
		
		return dataReturned.iterator();
	}

	@DataProvider(name="payloads")
	public Iterator<Object[]> payloads()
	{
		String[] nodes = tool.getNodes();
		ArrayList<Object[]> dataReturned = new ArrayList<Object[]>();
		
		for(String node:nodes)
		{
			if(!node.equals("SC-1")&&!node.equals("SC-2"))
				dataReturned.add(new Object[]{node});
		}
		
		return dataReturned.iterator();
	}

}

