package se.ericsson.jcat.litp.tests;

import se.ericsson.jcat.litp.fw.TC_TORRV_Func;
import se.ericsson.jcat.litp.utils.*;

import org.testng.annotations.Test;

/**
 * As a Test Engineer I want to check status of managed nodes after
 * installation.
 * 
 * @author esensen
 * 
 */
public class TC_TORRV80_Func_82 extends TC_TORRV_Func {

	public TC_TORRV80_Func_82(String name) {
		super(name);
	}

	public TC_TORRV80_Func_82(SSHConnect connect) {
		super(connect);
	}

	/**
	 * Single Node test case
	 */
	@Test
	public void logonNFS1() {

		// setTestcase("TC82: loggingSC1", "logging into SC-1");
		// setTestStep("Logging into SC-1");
		/*
		 * setTestcase("TC82: loggingNFS1", "logging into NFS-1");
		 * setTestStep("Logging into NFS-1");
		 * 
		 * String result =
		 * ssh.sendSshCommand("ssh nfs-1 'showmount -e nfs-1| wc -l'");
		 * setTestStep("No of Filesystem Shares");
		 * 
		 * setTestInfo(result);
		 */
		if (connect.getType().equals(BladeType.SINGLE))
		{
		setTestcase("TC82: FilesystemShares" + "--" + connect.getServer(),
				"List of Filesystems Created");
		String result1 = connect.getSsh().sendSshCommand(
				"ssh nfs-1 'showmount -e nfs-1'");
		setTestStep("Display Filesystem Mounts");
		setTestInfo(result1);
		}

	}

	//TODO: need a more general command to check the showmount command for both single node and multi node
	@Test(dataProvider = "nodes")
	public void logonSC(String node) {
		setTestcase("TC82: FilesystemShares:" + node + "--" + connect.getServer(),
				"Number of Filesystems mounted on " + node);
		String result = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'showmount -e nfs-1| wc -l'");
		setTestStep("No of Filesystem Shares");
		setTestInfo(result);

		setTestcase("TC82: FilesystemList: " + node + ": " + connect.getServer(),
				"List of filesystem mounted");
		String result1 = connect.getSsh().sendSshCommand(
				"ssh " + node + " 'showmount -e nfs-1'");
		setTestStep("List of Filesystems");
		setTestInfo(result1);

	}

}
