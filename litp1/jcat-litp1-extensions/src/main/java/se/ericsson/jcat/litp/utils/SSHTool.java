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
package se.ericsson.jcat.litp.utils;

import java.util.ArrayList;

public class SSHTool {
	private SSHConnect connect;
	
	public SSHTool(SSHConnect connect)
	{
		this.connect = connect;
	}
	
	public String[] getNodes()
	{
		//start new ssh session
		SshSession ssh = new SshSession(connect.getServer(),connect.getUser(),connect.getPassword());
		//init the return nodes
		String[] nodes = null;
		
		if(ssh.hasSSHClient())
		{
			//get the count of the managed nodes excluding the nfs node
			String countOfNodes = ssh.sendSshCommand("cobbler list |grep -i site|grep -v nfs|wc -l");
			
			//countOfNodes = "number\n", so we should remove the last two characters,
			int count = Integer.valueOf(countOfNodes.substring(0, countOfNodes.length()-1));
	
			if(countOfNodes!=null)
			{
				nodes = new String[count];
				//countOfNodes must >= 2
				nodes[0] = "SC-1";
				nodes[1] = "SC-2";
				for(int i = 3; i<= count;i++)
				{
					nodes[i-1] = "PL-"+ String.valueOf(i);
				}
				
			}	
			//close the connection
			ssh.disconnet();
		}
		return nodes;
	}
	
	public BladeType getBladeType()
	{
		BladeType bt = null;
		
		//start new ssh session
		SshSession ssh = new SshSession(connect.getServer(),connect.getUser(),connect.getPassword());
		
		if(ssh.hasSSHClient())
		{
			String result = ssh.sendSshCommand("cat /etc/hosts |grep -i nfs");
			if(result == null)
			{
				bt = BladeType.MULTI;
			}
			else
			{
				bt = BladeType.SINGLE;
			}
			//close the connection
			ssh.disconnet();
		}
		return bt;
	}
	
	public void authenticateNodes()
	{
		
		String[] nodes = getNodes();
 		//start new ssh session
		SshSession ssh = new SshSession(connect.getServer(),connect.getUser(),connect.getPassword());
		if(ssh.hasSSHClient())
		{
			for(String node:nodes)
			{
				//Permanently add managed node to Known_host
				ssh.sendSshCommand("ssh -o StrictHostKeyChecking=no " + node + " 'cmw-status node'");
				
			}
		}
		
		//close the connection
		ssh.disconnet();
	}

}
