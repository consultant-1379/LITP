/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 * Author: Shen Shen (esensen)
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package se.ericsson.jcat.litp.utils;

import se.ericsson.jcat.litp.utils.SshSession;

/**
 * This class is to define a SSHConnect for automation test for LITP
 * @author esensen
 *
 */
public class SSHConnect {
	private SshSession ssh = null;
	private String server = null;
	private String user = null;
	private String password = null;
	private BladeType type = null;
	/**
	 * @param server
	 * @param user
	 * @param password
	 * @param type
	 */
	public SSHConnect(String server, String user, String password,
			BladeType type) {
		super();
		this.server = server;
		this.user = user;
		this.password = password;
		this.type = type;
	}
	/**
	 * @param server
	 * @param user
	 * @param password
	 */
	public SSHConnect(String server, String user, String password) {
		super();
		this.server = server;
		this.user = user;
		this.password = password;
	}
	/**
	 * 
	 */
	public SSHConnect() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method to initialise SSH connection
	 */
	public void connect()
	{
		if(ssh==null)
		{
			ssh = new SshSession(server, user, password);
		}
	}
	/**
	 * Method to disconnect SSH connection
	 */
	public void disconnect()
	{
		if(ssh!=null)
		{
			ssh = null;
		}
	}
	
	
	/**
	 * @return the ssh
	 */
	public SshSession getSsh() {
		return ssh;
	}
	/**
	 * @param ssh the ssh to set
	 */
	public void setSsh(SshSession ssh) {
		this.ssh = ssh;
	}
	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}
	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the type
	 */
	public BladeType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(BladeType type) {
		this.type = type;
	}
	
	

}
