package se.ericsson.jcat.litp.fw;

import org.apache.log4j.Logger;
import se.ericsson.jcat.fw.ng.JcatNGTestBase;

/**
 * This is the java class that will be extended by all of our test classes. In
 * other words, this class will be called before every test that we run. It
 * extends from the <code>NonUnitTestCase</code> class from the JCAT framework.
 * The reason why we need it is that we cannot directly instantiate the
 * <code>NonUnitTestCase</code> as it is abstract.
 * 
 * @author emarmam
 */
public class LitpJcatNgTestCase extends JcatNGTestBase {

	public LitpJcatNgTestCase(String name) {
		super(name);
		/*
		 * Get Logger instance and set log levels.
		 */
		logger = Logger.getLogger(this.getClass());
	}

	protected LitpSut MYSUT = null;
}
