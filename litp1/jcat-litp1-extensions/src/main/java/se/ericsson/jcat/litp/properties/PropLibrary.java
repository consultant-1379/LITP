package se.ericsson.jcat.litp.properties;

import java.util.Properties;
import org.apache.log4j.Logger;
import se.ericsson.jcat.fw.utils.Cat2Utils;

/**
 * This class is used as a library that holds all the properties.
 * 
 * @author emarmam
 */
public class PropLibrary {
	private static Logger logger = Logger.getLogger("PropLibrary");
	private static PropLibrary propLib = null;

	public static String sshHost = null;
	public static String sshUser = null;
	public static String sshPass = null;
	public static String sshLitpAdmin = null;
	public static String sshLitpAdminPw = null;
	public static String sshLitpUser = null;

	private PropLibrary() {
		Properties props = Cat2Utils.getProperties();
		logger.info("Populating the property library");

		sshHost = getProperty(props, "ssh.host");
		sshUser = getProperty(props, "ssh.user");
		sshPass = getProperty(props, "ssh.pass");
		sshLitpAdmin = getProperty(props, "ssh.litp_admin");
		sshLitpAdminPw = getProperty(props, "ssh.litp_adminpw");
		sshLitpUser = getProperty(props, "ssh.litp_user");

		/*
		 * sshHost = "10.45.18.190"; sshUser = "root"; sshPass = "ciroot2";
		 */

	}

	private String getProperty(Properties props, String key) {
		String value = null;
		value = props.getProperty(key);
		if (value == null) {
			logger.error("Property " + "\"" + key + "\""
					+ " could not be found");
		}

		logger.info("Read properties " + "\"" + key + "\"" + " value: " + "\""
				+ value + "\"");

		return value;

	}

	public static PropLibrary getInstance() {
		if (propLib == null) {
			propLib = new PropLibrary();
		}
		return propLib;
	}
}
