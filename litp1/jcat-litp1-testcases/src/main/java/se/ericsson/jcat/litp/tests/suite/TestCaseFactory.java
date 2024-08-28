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
package se.ericsson.jcat.litp.tests.suite;

import java.util.ArrayList;
import org.testng.annotations.Factory;
import se.ericsson.jcat.litp.fw.*;
import se.ericsson.jcat.litp.tests.*;
import se.ericsson.jcat.litp.utils.SSHConnect;
import se.ericsson.jcat.litp.utils.SSHTool;

/**
 * Factory class to dynamically load the connection servers
 * 
 * @author esensen
 * 
 */
public class TestCaseFactory extends AbstractTestFactory {

	// private TestNG tng = new TestNG();

	public TestCaseFactory() {
		super();
	}

	/**
	 * To add new test into the factory, just add new instance to the arraylist.
	 * 
	 * @return
	 */
	@Factory
	public Object[] createInstances() {
		ArrayList<Object> ar = new ArrayList<Object>();
		Object[] result = null;
		for(SSHConnect connect:connections)
		{
			this.init(connect);
			
			//add test cases
			ar.add(new TC_TORRV80_Func_81(connect));
			ar.add(new TC_TORRV80_Func_82(connect));
			ar.add(new TC_TORRV80_Func_83(connect));
			ar.add(new TC_TORRV80_Func_84(connect));
			ar.add(new TC_TORRV80_Func_114(connect));
			ar.add(new TC_TORRV80_Func_141(connect));
			ar.add(new TC_TORRV80_Func_115(connect));
			// TODO: add new tests
		}
		result = new Object[ar.size()];
		result = ar.toArray();
		return result;
	}
	
	/**
	 * init ssh environment
	 * @param connect
	 */
	private void init(SSHConnect connect)
	{
		//set the BladeType for the server
		tool = new SSHTool(connect);
		connect.setType(tool.getBladeType());
		
		//authenticate all managed nodes
		tool.authenticateNodes();
	}

}
