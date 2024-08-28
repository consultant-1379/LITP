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

import org.testng.annotations.Factory;

import se.ericsson.jcat.litp.utils.*;

public abstract class AbstractTestFactory {
//	private static int no_testcase = 2;
	protected ArrayList<SSHConnect> connections = null;
	protected int count;
	protected static SSHTool tool;
	protected ArrayList<TC_TORRV_Func> testCases = null;
	private static String xmlPath = "./src/main/resources/server_list.xml";
	private static XMLParser xmlParser;
	
//	private TestNG tng = new TestNG();
	
	public AbstractTestFactory()
	{
		xmlParser = new XMLParser(xmlPath);
		connections = xmlParser.getServers();
		count = connections.size();
		testCases = new ArrayList<TC_TORRV_Func>();
		tool = null;
	}
	
	
	/**
	 * To add new test into the factory, just add new instance to the arraylist.
	 * @return
	 */
	@Factory
	protected abstract Object[] createInstances();
//	{
//		ArrayList<Object> ar = new ArrayList<Object>();
//		Object[] result = null;
//		for(int i =0;i<count;i++)
//		{
////			for(int j =0;j<testCases.size();j++)
////			{
////				testCases.get(j).setConnect(connections.get(i));
////				ar.add(testCases.get(j));
////			}
//			ar.add(new TC_TORRV80_Func_81(connections.get(i)));
//			ar.add(new TC_TORRV80_Func_82(connections.get(i)));
//			ar.add(new TC_TORRV80_Func_83(connections.get(i)));
//			ar.add(new TC_TORRV80_Func_84(connections.get(i)));
////			ar.add(new TC_TORRV80_Func_114(connections.get(i)));
//			
//		}
//		result = new Object[ar.size()];
//		result = ar.toArray();
//		return result;
//	}
//	
//	/**
//	 * method to add all test cases into the factory
//	 */
//	public void register()
//	{
//		testCases.add(new TC_TORRV80_Func_81("81"));
//		testCases.add(new TC_TORRV80_Func_82("82"));
//		testCases.add(new TC_TORRV80_Func_83("83"));
//		testCases.add(new TC_TORRV80_Func_84("84"));
//		//Add new test instance here
//	}
	
}
