package se.ericsson.jcat.litp.tests;

import org.testng.annotations.Test;

import se.ericsson.jcat.litp.fw.TC_TORRV_Func;
import se.ericsson.jcat.litp.utils.SSHConnect;

public class TC_TORRV80_Func_83 extends TC_TORRV_Func {

	public TC_TORRV80_Func_83(String name) {
		super(name);
	}

	public TC_TORRV80_Func_83(SSHConnect connect) {
		super(connect);
	}

	@Test(dataProvider = "nodes")
	public void cmwStatusSC(String node) {

		setTestcase(
				"TC83: cmwStatus: " + node + "--" + connect.getServer(),
				"CMW Status " + node);
		String result = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'cmw-status node'");
		setTestStep("CMW Status on " + node);
		setTestInfo(result);
		saveAssertTrue("CMW not Running on " + node,
				result.contains("Status OK"));
	}

	@Test(dataProvider = "nodes")
	public void openSAFCampaign(String node) {
		setTestcase(
				"TC83: openSAF: " + node + "--" + connect.getServer(),
				"CMW openSAF list on " + node);
		String cmd = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'cmw-repository-list --campaign | xargs cmw-campaign-status|grep -i opensaf'");
		setTestStep("CMW openSAF list on " + node);

		saveAssertTrue("openSAF is not committed", (!(cmd==null))&&cmd.contains("COMMITTED"));
		
	}
	
	@Test(dataProvider = "nodes")
	public void opendjCampaign(String node) {
		setTestcase(
				"TC83: openDJ: " + node + "--" + connect.getServer(),
				"CMW openDJ list on " + node);
		String cmd = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'cmw-repository-list --campaign | xargs cmw-campaign-status|grep -i opendj'");
		setTestStep("CMW openDJ list on " + node);
		saveAssertTrue("openDJ is not committed", (!(cmd==null))&&cmd.contains("COMMITTED"));
	}
	
	//Jboss testing st-2686
	
	@Test(dataProvider = "nodes")
	public void checkForJBossCampaign(String node) {
		setTestcase(
				"TC83: Jboss: " + node + "--" + connect.getServer(),
				"CMW Jboss list on " + node);
		String cmd = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'cmw-repository-list --campaign | xargs cmw-campaign-status|grep -i jboss'");
		setTestStep("CMW Jboss list on " + node);
		saveAssertTrue("Jboss is not committed", (!(cmd==null))&&cmd.contains("COMMITTED"));
	}

	
	@Test(dataProvider = "nodes")
	public void jbossService(String node) {
		setTestcase(
				"TC83: checks jboss service: " + node + "--" + connect.getServer(),
				"CMW jboss service list on " + node);
		String cmd = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'service jboss-eap status'");
		setTestStep("jboss service " + node);
		saveAssertTrue("Service is not running", (!(cmd==null))&&cmd.contains("is running"));
	}
	
	// TO Do
	@Test(dataProvider = "nodes")
	public void jbossasApp(String node) {
		setTestcase(
				"TC83: checks jboss Application: " + node + "--" + connect.getServer(),
				"CMW jboss service list on " + node);
		String cmd = connect.getSsh().sendSshCommand(
				"ssh " + node + "curl http://"+node+":8080/jboss-as-helloworld/HelloWorld");
		setTestStep("jbossas Application " + node);
		saveAssertTrue("Application is not running", (!(cmd==null))&&cmd.contains("Hello Ericsson!!!!"));
	}
	
	@Test(dataProvider = "nodes")
	public void jbossVersion(String node) {
		setTestcase(
				"TC83: checks jboss service: " + node + "--" + connect.getServer(),
				"CMW jboss service list on " + node);
		String cmd = connect.getSsh().sendSshCommand(
				"ssh " + node + " '/bin/cat /opt/jboss-eap/welcome-content/documentation.html |grep -i EAP.6'");
		setTestStep("jboss service " + node);
		saveAssertTrue("Version do not match", (!(cmd==null))&&cmd.contains("EAP 6"));
	}
	
	@Test(dataProvider = "nodes")
	public void jbossPackage(String node) {
		setTestcase(
				"TC83: checks jboss Package: " + node + "--" + connect.getServer(),
				"CMW jboss service list on " + node);
		String cmd = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'rpm -qa |grep jboss-eap'");
		setTestStep("jboss Package on " + node);
		saveAssertTrue("Jboss package not installed", (!(cmd==null))&&cmd.contains("jboss-eap"));
	}
	
	
}
