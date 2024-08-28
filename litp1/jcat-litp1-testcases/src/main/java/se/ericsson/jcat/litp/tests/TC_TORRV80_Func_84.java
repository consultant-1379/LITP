package se.ericsson.jcat.litp.tests;

import se.ericsson.jcat.fw.annotations.JcatClass;
import se.ericsson.jcat.litp.fw.TC_TORRV_Func;
import se.ericsson.jcat.litp.utils.*;
import org.testng.annotations.Test;

/**
 * Components test components link: https://team.ammeon.com/confluence/display/LITPExt/Landscape+Components
 * @author esensen
 * ignore comment
 */

public class TC_TORRV80_Func_84 extends TC_TORRV_Func {

	public TC_TORRV80_Func_84(String name) {
		super(name);
	}

	public TC_TORRV80_Func_84(SSHConnect connect) {
		super(connect);
	}

	/*@Test
	public void blades_HPBL46x() {

		if (connect.getType().equals(BladeType.MULTI))
		{
			setTestcase("TC84: blades.HPBL46x" + "--" + connect.getServer(), "Checking Blade");
			//TODO: doesn't work
			String cmd = connect.getSsh().sendSshCommand(
					"litp /inventory/site1/enclosures/blade verify |grep -i error");
			setTestStep("blades.HPBL46x Component");
			saveAssertFalse("Component not installed",
					cmd!=null);
		}
		else{			
		}
		*/

	/*}*/
/*
	@Test
	public void blades_HPBL46xBios() {
		
		if (connect.getType().equals(BladeType.MULTI))
		{
		setTestcase("TC84: blades.HPBL46xBios" + "--" +connect.getServer(),
				"Checking Blade Bios");
		//TODO: doesn't work
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/systems/blade/bios verify | grep -i error");
		setTestStep("blades.HPBL46xBios Component");
		saveAssertFalse("Component not installed",
					cmd!=null);
		}
	}*/
	
	//TODO: blades.HPC7000Enclosure Command has problem
//	@Test
//	public void blades_HPC7000Enclosure()
//	{}
	
	//TODO: component name cmw.CmwCluster - cmw.Campaign
	@Test()
	public void cmw_CmwCluster() {

		setTestcase("TC84: cmw.CmwCluster" + "--" +connect.getServer(),
				"Checking CMW Cluster");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/cmw_installer verify |grep -i error");
		setTestStep("cmw.CmwCluster Component");
		
		saveAssertFalse("Component not installed",
					cmd!=null);

	}

	@Test()
	public void cmw_Lde() {

		setTestcase("TC84: cmw.Lde" + "--" +connect.getServer(), "Checking lde");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/sc1/lde verify |grep -i error");
		setTestStep("cmw.Lde Component");
		saveAssertFalse("Component not installed",
				cmd!=null);

	}

	//TODO: not listed in confluence
	@Test()
	public void tipc_lde() {

		setTestcase("TC84: tipc_lde" + "--" +connect.getServer(), "Checking TIPC NET ID");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/sc1/lde/tipc verify |grep -i error");
		setTestStep("Cobbler Server plugin");
		saveAssertFalse("Component not installed",
				cmd!=null);

	}

	@Test()
	public void cobbler_Server() {

		setTestcase("TC84: cobbler.server" + "--" +connect.getServer(),
				"Checking Cobbler Server plugin");
		String cmd = connect
				.getSsh()
				.sendSshCommand(
						"litp /inventory/site1/cluster1/ms1/ms_boot/bootservice verify |grep -i error");
		setTestStep("Cobbler Server plugin");
		saveAssertFalse("Component not installed",
				cmd!=null);

	}

	@Test()
	public void cobbler_server_KickstartManager() {

		setTestcase("TC84: cobbler.server.KickstartManager" + "--" +connect.getServer(),
				"Checking KickStart Manager");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/ms_boot/ksmanager verify |grep -i error");
		setTestStep("cobbler.server.KickstartManager Component");
		saveAssertFalse("Component not installed",
				cmd!=null);

	}

	/*@Test() //TODO
	public void cobbler_server_KSPartitionManager() {
		if (connect.getType().equals(BladeType.MULTI))
		{
		setTestcase("TC84: cobbler.server.KSPartitionManager" + "--" +connect.getServer(),
				"Checking KickStart Partition Manager");
		//TODO: command doesn't work
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/enclosures/dl380/bios verify |grep -i error");
		setTestStep("cobbler_server_KSPartitionManager Component");
		saveAssertFalse("Component not installed",
					cmd!=null);
		}
		else{
			
		}
		}

	}*/

	
	@Test
	public void fireWalls() {
		setTestcase("TC84: firewall" + "--" +connect.getServer(),
				"validate firewall");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/os/fw_basetcp validate |grep -i error");
		setTestStep("validate firewall component");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}
	
	/**
	 * single node passed without error
	 *//*
	@Test
	public void hardware_generic_system_GenericSystem() {
		if (connect.getType().equals(BladeType.MULTI))
		{
		setTestcase("TC84: hardware.generic.system" + "--" +connect.getServer(),
				"Checking Hardware Generic System");
		//TODO: doesn't work
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/systems/blade/bios verify | grep -i error");
		setTestStep("hardware.generic.system Component");
		saveAssertFalse("Component not installed",
					cmd!=null);
		}

	}*/
	/**
	 * single node passed without error
	 */
	/*@Test
	public void hardware_QLogicQMH2562() {
		if (connect.getType().equals(BladeType.MULTI))
		{
		setTestcase("TC84: hardware.QLogicQMH2562" + "--" + connect.getServer(),
				"Checking Qlogic card");
		//TODO: doesn't work
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/systems/blade/bios verify |grep -i error");
		setTestStep("hardware_QLogicQMH2562 Component");
		saveAssertFalse("Component not installed",
					cmd!=null);
		}

	}*/
	
	@Test 
	public void hypericAgent() {
		setTestcase(
				"TC84: hyperic.hyagent.HyAgent: --" + connect.getServer(),
				"verify hyagent component");
	
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/hypericagent/hyagent verify |grep -i error");
		setTestStep("hypericAgent Component");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}

//	@Test  
//  Command has problem
//	public void hypericComponents() {
//	}
	
	
	@Test
	public void hypericServer() {
		setTestcase(
				"TC84: hyperic.hyserver.HyServer: --" + connect.getServer(),
				"verify hyServer component");
	
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/hypericserver/hyserver verify |grep -i error");
		setTestStep("hypericServer Component");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}
	
	@Test()
	public void kickstart_Kickstart() {

		setTestcase("TC84: kickstart.Kickstart" + "--" +connect.getServer(),
				"Checking kickstart component");
		//TODO: command doesn't work, /systems doesn't exist
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/os/ks verify |grep -i error");
		setTestStep("kickstart.Kickstart Component");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}
	
	//TODO: kickstart.KSPartition Command has problem
//	@Test
//	public void kickstart_KSPartition()
//	{}
	
	//TODO: Litp flow diagram
//	@Test
//	public void litp_flow_diagram()
//	{}
	
	//TODO: litputil.File
	/*@Test()
	public void litputil_File() {

		setTestcase("TC84: Litp_Util" + "--" +connect.getServer(),
				"Checking litp util Component");
		//TODO: command doesn't work
		String cmd = connect.getSsh().sendSshCommand(
				"litp /definition/os/file verify |grep -i error");
		setTestStep("LITP Utilities");
		saveAssertFalse("Component not installed",
				cmd!=null);

	}*/
	
	/*@Test
	public void litputil_Package() {
		setTestcase("TC84: litputil_Package" + "--" +connect.getServer(),
				"Checking litputil package Component");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/libvirt verify |grep -i error");
		setTestStep("LITP Repository");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}
*/

	@Test
	public void litputil_Repository() {
		setTestcase("TC84: litputil.Repository" + "--" +connect.getServer(),
				"Checking litputil Repository Component");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/sc1/repository/repo1 verify |grep -i error");
		setTestStep("LITP Repository");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}
	

	@Test()
	public void mysql_MYSQLServer() {

		setTestcase("TC84: mySqlServer" + "--" +connect.getServer(),
				"Checking SQL Server Component");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/mysqlserver/config verify |grep -i error");
		setTestStep("MySQL Server Component");
		saveAssertFalse("Component not installed",
				cmd!=null);

	}
	

	
	@Test
	public void nas_NASClient() {
		setTestcase("TC84: nas.NASClient" + "--" +connect.getServer(),
				"Checking NASClient Component ");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/sc1/sfs/sfs_share_1/ exec verify |grep -i error");
		setTestStep("verify nsa client");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}
	
	@Test
	public void nas_NASService() {
		setTestcase("TC84: nas_NASService" + "--" +connect.getServer(),
				"Checking nas NASService Component ");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/sfs/export1 verify |grep -i error");
		setTestStep("nas_NASService");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}
	

	@Test
	public void network_ipaddress_IPAddress() {
		setTestcase("TC84: network.ipaddress.IPAddress" + "--" +connect.getServer(),
				"Checking network ipaddress IPAddress Component ");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/os/ip verify |grep -i error");
		setTestStep("verify ipaddress component");
		saveAssertFalse("Component not installed",
				cmd!=null);

	}

	//TODO: network.network_address.NetworkAddress command has problem 
	// Command has problem
//	@Test
//	public void network_network_address_NetworkAddress()
//	{}
	
	//TODO: network.networkaddress.NetworkAddress
	//TODO: same as the the above one?
//	@Test
//	public void networknetwork_address_NetworkAddress()
//	{}
	
	@Test
	public void network_tipcaddress_TipcAddress() {
		setTestcase("TC84: network_tipcaddress_TipcAddress" + "--" +connect.getServer(),
				"Checking network tipcaddress TipcAddress Component ");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/pl3/lde/tipc verify |grep -i error");
		setTestStep("network_tipcaddress_TipcAddress");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}
	
	@Test
	public void ntp_NtpClient() {
		setTestcase("TC84: ntp_NtpClient" + "--" +connect.getServer(),
				"Checking ntp NtpClient Component ");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/sc1/os/ntp1 verify |grep -i error");
		setTestStep("ntp NtpClient");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}
	
	@Test
	public void operatingsystem_generic_os_GenericOs() {
		setTestcase("TC84: operatingsystem.generic_os.GenericOs" + "--" +connect.getServer(),
				"Checking operatingsystem.generic_os.GenericOs ");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/os verify |grep -i error");
		setTestStep("GenericOs");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}
	
	//TODO: operatingsystem.RHELOS
	@Test
	public void operatingsystem_RHELOS() {
		setTestcase("TC84: operatingsystem.RHELOS" + "--" +connect.getServer(),
				"Checking operatingsystem.RHELOS ");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/os verify |grep -i error");
		setTestStep("verify operatingsystem.RHELOS");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}
	
	@Test
	public void puppet_server_PuppetDashboard() {
		setTestcase("TC84: puppet_server_PuppetDashboard" + "--" +connect.getServer(),
				"Checking puppet server PuppetDashboard Component ");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/puppetdashboard verify |grep -i error");
		setTestStep("operatingsystem generic os GenericOs");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}
	
	//redundancy 
//	@Test()
//	public void puppetDashboard() {
//
//		setTestcase("TC84: puppetDashboard" + "--" +connect.getServer(),
//				"Checking Puppet Dashboard");
//		String cmd = connect.getSsh().sendSshCommand(
//				"litp /inventory/site1/cluster1/ms1/puppetdashboard verify");
//		String[] result;
//		setTestStep("Puppet Dashboard Component");
//		result = cmd.split("//n");
//
//		for (int i = 0; i < result.length; i++) {
//			setTestInfo(result);
//			saveAssertFalse("Component not installed",
//					result[i].contains("error"));
//
//		}
//
//	}
	
	@Test
	public void rlogging_rlogclient() {
		setTestcase("TC84: rlogging_rlogclient" + "--" +connect.getServer(),
				"Checking rlogging rlogclient Component ");
		String cmd = connect
				.getSsh()
				.sendSshCommand(
						"litp /inventory/site1/cluster1/sc1/syslog_central/rsyslog_server verify |grep -i error");
		setTestStep("rlogging rlogclient");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}

	@Test
	public void rlogging_rlogserver() {
		setTestcase("TC84: rlogging_rlogserver" + "--" +connect.getServer(),
				"Checking rlogging rlogserver Component ");
		String cmd = connect
				.getSsh()
				.sendSshCommand(
						"litp /inventory/site1/cluster1/sc1/syslog_central/rsyslog_server verify |grep -i error");
		setTestStep("rlogging rlogserver");
		saveAssertFalse("Component not installed",
				cmd!=null);
	}

	//TODO: servers.IpmiSystem (and Pool)
	// Command has problem
	/*@Test()
	public void servers_IpmiSystem() {

		setTestcase("TC84: servers.IpmiSystem" + "--" +connect.getServer(),
				"Checking IPMI Status");
		//TODO: command doesn't work
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/systems/vm_pool verify |grep -i error");
		setTestStep("servers.IpmiSystem Component");
		saveAssertFalse("Component not installed",
				cmd!=null);

	}*/
	
	/**
	 * Multi
	 */
	@Test
	public void storage_StoragePoolSAN() {
		if (connect.getType().equals(BladeType.MULTI))
		{
		setTestcase("TC84: storage.StoragePoolSAN" + "--" +connect.getServer(),
				"Checking SAN Storage");
		//TODO: modified
		String cmd = connect.getSsh().sendSshCommand(
//				"/inventory/site1/san verify");
				"litp /inventory/site1/san verify |grep -i error");
		setTestStep("storage.StoragePoolSAN Component");
		saveAssertFalse("Component not installed",
				cmd!=null);
		}

	}
	
	@Test()
	public void sudoersComponents() {

		setTestcase("TC84: sudoers" + "--" +connect.getServer(),
				"Checking Sudoers Component");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/rd_sudoers verify | grep -i error");
		setTestStep("Sudoers Component");
		saveAssertFalse("Component not installed",
				cmd!=null);

	}
	
	//TODO: User and Group Components should there be a groupComponents test?
	@Test()
	public void userComponents() {

		setTestcase("TC84: users" + "--" +connect.getServer(), "Checking Users Component");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/users verify |grep -i error");
		setTestStep("Users Component");
		saveAssertFalse("Component not installed",
				cmd!=null);

	}
	
	//TODO: not specified in the confluence, is it group components test?
	@Test()
	public void clusterStatus() {

		setTestcase("TC84: Cluster_Status" + "--" +connect.getServer(),
				"Checking Cluster Component");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ verify | grep -i error");
		setTestStep("Cluster Status");
		saveAssertFalse("Component not installed",
				cmd!=null);

	}
	
	/**
	 * single
	 */
	@Test
	public void virt_VMServiceSolaris() {
		if (connect.getType().equals(BladeType.SINGLE))
		{
		setTestcase("TC84: virt.VMServiceSolaris" + "--" +connect.getServer(),
				"Checking VM Service");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/libvirt/vmservice verify | grep -i error");
		setTestStep("virt.VMServiceSolaris Component");
		saveAssertFalse("Component not installed",
				cmd!=null);
		}

	}

	/**
	 * Libvirt method in xml file?
	 */
	@Test
	public void virtplugin() {
		if (connect.getType().equals(BladeType.SINGLE))
		{
		setTestcase("TC84: libVirt" + "--" +connect.getServer(),
				"Checking LibVirt Component");
		String cmd = connect.getSsh().sendSshCommand(
				"litp /inventory/site1/cluster1/ms1/libvirt verify |grep -i error");
		setTestStep("libVirt plugin");
		saveAssertFalse("Component not installed",
				cmd!=null);
		}

	}

	//Removed from component list
//	@Test()
//	public void nfsMount() {
//
//		setTestcase("TC84: nasNFS.Mount" + "--" +connect.getServer(),
//				"Checking NFS Mount Component");
//		
//		//TODO:command "litp /inventory/site1/cluster1/sc1/sfs verify"
//		String cmd = connect.getSsh().sendSshCommand(
////				"litp /inventory/site1/cluster1/sc1/sfs_client verify");
//				"litp /inventory/site1/cluster1/sc1/sfs verify");
//		String[] result;
//		setTestStep("NFS Mount");
//		result = cmd.split("//n");
//
//		for (int i = 0; i < result.length; i++) {
//			setTestInfo(result);
//			saveAssertFalse("Component not installed",
//					result[i].contains("error"));
//
//		}
//
//	}


/*
 * 	blades.HPC7000Enclosure
 *  Hyperic componenents
 *  kickstart.KSPartition
 *  Litp flow diagram
 *  litputil.File
 *  network.network_address.NetworkAddress
 *  network.networkaddress.NetworkAddress
 *  servers.IpmiSystem (and Pool)   -- command has problem
 *  storage.StoragePoolSAN_SP13
 *  
 */








}