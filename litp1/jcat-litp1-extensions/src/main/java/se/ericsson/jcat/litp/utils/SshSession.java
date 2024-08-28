package se.ericsson.jcat.litp.utils;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.KBIAuthenticationClient;
import com.sshtools.j2ssh.authentication.KBIPrompt;
import com.sshtools.j2ssh.authentication.KBIRequestHandler;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.connection.ChannelInputStream;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;

/**
 * This class is used to establish the connection to our server SUT. It opens
 * and authenticates the session with the username and password provided in the
 * constructor.
 * 
 * @author emarmam
 */
public class SshSession {

	private SshClient ssh;
	public static Logger logger = Logger.getLogger("SshSession");

	/**
	 * The class constructor that initializes the connection
	 * 
	 * @param ip
	 *            - server IP
	 * @param userName
	 *            - server username for SSH
	 * @param password
	 *            - server password for SSH
	 */
	public SshSession(String ip, String userName, String password) {
		// Calls the method that starts the SSH session
		// this.ssh = this.openSshConnectionKeyboardInteractive(ip, userName,
		// password);
		this.ssh = this.openSshConnectionPasswordInteractive(ip, userName,
				password);

	}

	/**
	 * Used for password interactive connection
	 * 
	 * @param ip
	 *            - server IP
	 * @param userName
	 *            - server username for SSH
	 * @param password
	 *            - server password for SSH
	 * @return SshClient
	 */
	private SshClient openSshConnectionPasswordInteractive(String ip,
			String userName, String password) {
		try {
			ssh = new SshClient();
			ssh.setSocketTimeout(30000);
			ssh.connect(ip, new IgnoreHostKeyVerification());
			PasswordAuthenticationClient pac = new PasswordAuthenticationClient();
			pac.setUsername(userName);
			pac.setPassword(password);

			int result = ssh.authenticate(pac);

			if (result != AuthenticationProtocolState.COMPLETE) {
				String resultString = null;
				switch (result) {
				case AuthenticationProtocolState.CANCELLED:
					resultString = "CANCELLED";
					break;
				case AuthenticationProtocolState.COMPLETE:
					resultString = "COMPLETE";
					break;
				case AuthenticationProtocolState.FAILED:
					resultString = "FAILED";
					break;
				case AuthenticationProtocolState.PARTIAL:
					resultString = "PARTIAL";
					break;
				case AuthenticationProtocolState.READY:
					resultString = "READY";
					logger.info("Make sure the target sshd_config accepts Keyboard Authentication");
					break;
				default:
					break;
				}
				logger.error("Wrong SSH connection result state: "
						+ resultString);
				return null;
			} else {
				logger.info("SSH Connection result: COMPLETE");
			}

		} catch (IOException e) {
			logger.error("Failed to open SSH connection due to exception: "
					+ e.toString());
			return null;
		}

		return ssh;

	}

	/**
	 * Used for keyboard interactive connection
	 * 
	 * @param ip
	 *            - server IP
	 * @param userName
	 *            - server username for SSH
	 * @param password
	 *            - server password for SSH
	 * @return SshClient
	 */
	private SshClient openSshConnectionKeyboardInteractive(String ip,
			String userName, String password) {
		try {
			final String pwd = password;
			ssh = new SshClient();
			ssh.setSocketTimeout(30000);
			ssh.connect(ip, new IgnoreHostKeyVerification());
			KBIAuthenticationClient kbc = new KBIAuthenticationClient();
			kbc.setUsername(userName);
			kbc.setKBIRequestHandler(new KBIRequestHandler() {
				public void showPrompts(String name, String instructions,
						KBIPrompt[] prompts) {
					if (prompts != null) {
						for (int i = 0; i < prompts.length; i++) {
							logger.debug("Prompt: " + prompts[i].getPrompt());
							if (prompts[i].getPrompt().equals("Password: ")) {
								logger.debug("Setting response: " + pwd);
								prompts[i].setResponse(pwd);
							}
						}
					}
				}
			});

			int result = ssh.authenticate(kbc);

			if (result != AuthenticationProtocolState.COMPLETE) {
				String resultString = null;
				switch (result) {
				case AuthenticationProtocolState.CANCELLED:
					resultString = "CANCELLED";
					break;
				case AuthenticationProtocolState.COMPLETE:
					resultString = "COMPLETE";
					break;
				case AuthenticationProtocolState.FAILED:
					resultString = "FAILED";
					break;
				case AuthenticationProtocolState.PARTIAL:
					resultString = "PARTIAL";
					break;
				case AuthenticationProtocolState.READY:
					resultString = "READY";
					logger.info("Make sure the target sshd_config accepts Keyboard Authentication");
					break;
				default:
					break;
				}
				logger.error("Wrong SSH connection result state: "
						+ resultString);
				return null;
			} else {
				logger.info("SSH Connection result: COMPLETE");
			}

		} catch (IOException e) {
			logger.error("Failed to open SSH connection due to exception: "
					+ e.toString());
			return null;
		}

		return ssh;

	}

	public String sendSshCommand(String command) {
		SessionChannelClient session = null;
		ChannelInputStream inputStream = null;
		String theOutput = null;
		try {
			session = this.ssh.openSessionChannel();
			if (session.executeCommand(command)) {
				inputStream = session.getInputStream();
				byte buffer[] = new byte[255];
				int read;
				while ((read = inputStream.read(buffer)) > 0) {
					theOutput = new String(buffer, 0, read);
				}
			}
			session.close();
		} catch (IOException e) {
			logger.error("Failed to invoke SSH shell command");
			e.printStackTrace();
		}

		return theOutput;

	}
	
	/**
	 * disconnect from ssh client
	 * author: esensen
	 */
	public void disconnet()
	{
		if(ssh!=null)
		{
			ssh.disconnect();
		}
	}
	
	/**
	 * Is ssh client created
	 */
	public boolean hasSSHClient()
	{
		return !(this.ssh == null);
	}
}
