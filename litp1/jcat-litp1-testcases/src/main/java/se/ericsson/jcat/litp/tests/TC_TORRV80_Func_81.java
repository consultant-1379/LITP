package se.ericsson.jcat.litp.tests;

import se.ericsson.jcat.litp.fw.TC_TORRV_Func;
import se.ericsson.jcat.litp.utils.*;

import org.testng.annotations.Test;

public class TC_TORRV80_Func_81 extends TC_TORRV_Func {

	public TC_TORRV80_Func_81(String name) {
		super(name);
	}

	public TC_TORRV80_Func_81(SSHConnect connect) {
		super(connect);
	}

	// 4 deafult test cases on version ms1, sc-1, sc2, pl3, and pl4
	
	@Test
	public void checkVersionms1() {

		setTestcase("TC81: LITP_Version" + connect.getServer(),
				"Checking LITP Version");
		setTestStep("Connecting to the " + connect.getServer());
		setTestInfo("Using this username: " + connect.getUser());
		setTestInfo("Using this password: " + connect.getPassword());

		String result = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1 show version |grep -i litp");

		setTestStep("LITP version");
		setTestInfo(result);
		saveAssertTrue("Version not matched", result.contains("LITP Version"));

	}

	@Test
	public void checkVersionsc1() {

		setTestcase("TC81: LITP_Version" + connect.getServer(),
				"Checking LITP Version");
		setTestStep("Connecting to the " + connect.getServer());
		setTestInfo("Using this username: " + connect.getUser());
		setTestInfo("Using this password: " + connect.getPassword());

		String result = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/sc1 show version |grep -i litp");

		setTestStep("LITP version");
		setTestInfo(result);
		saveAssertTrue("Version not matched", result.contains("LITP Version"));

	}
	@Test
	public void checkVersionsc2() {

		setTestcase("TC81: LITP_Version" + connect.getServer(),
				"Checking LITP Version");
		setTestStep("Connecting to the " + connect.getServer());
		setTestInfo("Using this username: " + connect.getUser());
		setTestInfo("Using this password: " + connect.getPassword());

		String result = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/sc2 show version |grep -i litp");

		setTestStep("LITP version");
		setTestInfo(result);
		saveAssertTrue("Version not matched", result.contains("LITP Version"));

	}
	@Test
	public void checkVersionpl3() {

		setTestcase("TC81: LITP_Version" + connect.getServer(),
				"Checking LITP Version");
		setTestStep("Connecting to the " + connect.getServer());
		setTestInfo("Using this username: " + connect.getUser());
		setTestInfo("Using this password: " + connect.getPassword());

		String result = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/pl3 show version |grep -i litp");

		setTestStep("LITP version");
		setTestInfo(result);
		saveAssertTrue("Version not matched", result.contains("LITP Version"));

	}
	@Test
	public void checkVersionpl4() {

		setTestcase("TC81: LITP_Version" + connect.getServer(),
				"Checking LITP Version");
		setTestStep("Connecting to the " + connect.getServer());
		setTestInfo("Using this username: " + connect.getUser());
		setTestInfo("Using this password: " + connect.getPassword());

		String result = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/pl4 show version |grep -i litp");

		setTestStep("LITP version");
		setTestInfo(result);
		saveAssertTrue("Version not matched", result.contains("LITP Version"));

	}
	
	// version testcase finish here
	
	//ntp server 159.107.173.12 for sc-1 and sc-2
	//ntp sync for pl should be from sc-1 and sc-2
	
	
	@Test
	public void ntp_sc1() {
		setTestcase("TC81: ntp_sc1", "Cheking NTP Server on SC-1");
		String result = connect.getSsh().sendSshCommand("ssh sc-1 'cat /etc/ntp.conf|grep server'");
		setTestStep("NTP server for SC-1");
		setTestInfo(result);
		saveAssertTrue("No NTP Server", result.contains("159.107.173.12"));
	}
	
	@Test
	public void ntp_sc2() {
		setTestcase("TC81: ntp_sc2", "Cheking NTP Server on SC-2");
		String result = connect.getSsh().sendSshCommand("ssh sc-2 'cat /etc/ntp.conf|grep server'");
		setTestStep("NTP server for SC-2");
		setTestInfo(result);
		saveAssertTrue("No NTP Server", result.contains("159.107.173.12"));
	}
	
@Test(dataProvider = "payloads")
	public void ntp_pl(String node) {
		setTestcase("TC81: Checking NTP on Payload:" + node + "--" + connect.getServer(),
				"Servers synched with Payload " + node);
		String result = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'cat /etc/ntp.conf|grep server'");
		setTestStep("NTP Servers");
		setTestInfo(result);

		saveAssertTrue("NTP Servers Not Synced", result.contains("SC"));

	}
	
// ntp server testcase finish here	

//checking selinux on machine


@Test
public void sestatus_ms1() {
	setTestcase("TC81: checkSelinuxStatus", "Cheking selinux Server on ms1");
	String result = connect.getSsh().sendSshCommand("sestatus |grep -i current");
	setTestStep("Selinux Status");
	setTestInfo(result);
	saveAssertTrue("selinux not installed", result.contains("enforcing"));
}

@Test(dataProvider = "nodes")
public void sestatus_MNs(String node) {
	setTestcase("TC81: CheckingSelinux" + node + "--" + connect.getServer(),
			"Checking Selinux" + node);
	String result = connect.getSsh().sendSshCommand(
			"ssh " + node + " 'sestatus |grep -i current'");
	setTestStep("Selinux Status");
	setTestInfo(result);

	saveAssertTrue("selinux not installed", result.contains("enforcing"));

}
	
	/**
	 * Single Node test case
	 */
	@Test
	public void virshList() {
		if (connect.getType().equals(BladeType.SINGLE)) {
			setTestcase("TC81: ManagedNodesCount" + connect.getServer(),
					"No of Virtual Machines");
			String number = connect.getSsh().sendSshCommand(
					"virsh list|grep -i vm_site|wc -l");
			setTestStep("No of Virtual Machines");
			setTestInfo(number);

			setTestcase("TC81: ManagedNodes", "List of Virtual Machines");
			String nodes = connect.getSsh().sendSshCommand("virsh list");
			setTestStep("List of Managed Nodes");
			setTestInfo(nodes);

			saveAssertTrue("There are no Managed Nodes", nodes.contains("vm"));
		}
	}

	@Test
	public void puppetStatus() {

		setTestcase("TC81: puppetStatus" + connect.getServer(),
				"Checking Puppet Status");
		String result = connect.getSsh()
				.sendSshCommand("service puppet status");
		setTestStep("Puppet Status:");
		setTestInfo(result);
		saveAssertTrue("puppet is not Running", result.contains("is running"));
	}

	@Test
	public void puppetMasterStatus() {

		setTestcase("TC81: puppetMasterStatus" + connect.getServer(),
				"Checking PuppetMaster Status");
		String result = connect.getSsh().sendSshCommand(
				"service puppetmaster status");
		setTestStep("Puppet Master Status:");
		setTestInfo(result);
		saveAssertTrue("PuppetMaster is not Running",
				result.contains("is running"));
	}

	@Test
	public void cobblerList() {

		setTestcase("TC81: CobblerList" + connect.getServer(),
				"Checking Cobbler List");
		String result = connect.getSsh().sendSshCommand("cobbler list");
		setTestStep("Cobbler Configuration:");
		setTestInfo(result);

		setTestcase("TC81: CobblerStatus", "Check Cobbler Status");
		String cobblerd = connect.getSsh().sendSshCommand(
				"service cobblerd status");
		setTestStep("Cobbler Status:");
		setTestInfo(cobblerd);
		saveAssertTrue("Cobbler is not Running",
				cobblerd.contains("is running"));

	}

	@Test
	public void ipTables() {

		setTestcase("TC81: Firewall" + connect.getServer(),
				"Checking Firewall / IP Tables");
		// TODO: modified
		// String result =
		// connect.getSsh().sendSshCommand("service iptables status");
		String result = connect.getSsh()
				.sendSshCommand("lsmod |grep ip_tables");
		setTestStep("Firewall Configuration:");
		setTestInfo(result);
		saveAssertFalse("Firewall Not Running", result == null);
	}
	
	@Test
	public void landscapeService() {

		setTestcase("TC81: Landscape Service" + connect.getServer(),
				"Checking landscape service Status");
		String result = connect.getSsh()
				.sendSshCommand("service landscaped status");
		setTestStep("Landscape Service:");
		setTestInfo(result);
		saveAssertTrue("landscape servcie is not Running", result.contains("is running"));
	}

	/**
	 * litpAdmin doesn't exist now
	 */
	// @Test
	// public void litpAdmin() {
	//
	// setTestcase("TC81: litpAdmin", "User litp_admin Status");
	//
	// ssh = new SshSession(PropLibrary.sshHost, PropLibrary.sshLitpAdmin,
	// PropLibrary.sshLitpAdminPw);
	//
	// String result = ssh.sendSshCommand("whoami");
	// setTestStep("litp_admin user");
	// setTestInfo(result);
	// saveAssertTrue("litp_admin not created", result.contains("litp_admin"));
	// }
	/**
	 * litpUser doesn't exist now
	 */
	// @Test
	// public void litpUser() {
	//
	// setTestcase("TC81: litpUser", "User litp_user Status");
	//
	// ssh = new SshSession(PropLibrary.sshHost, PropLibrary.sshLitpUser,
	// PropLibrary.sshLitpAdminPw);
	//
	// String result = ssh.sendSshCommand("whoami");
	// setTestStep("litp_User user");
	// setTestInfo(result);
	// saveAssertTrue("litp_User not created", result.contains("litp_user"));
	// }

}
