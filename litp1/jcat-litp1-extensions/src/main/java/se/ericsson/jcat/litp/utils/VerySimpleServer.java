package se.ericsson.jcat.litp.utils;

//Tue Nov  2 18:33:43 EST 2004
//
//Written by Sean R. Owens, released to the public
//domain.  Share and enjoy.  http://darksleep.com/player

import java.net.Socket;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

/**
 * An example of a very simple socket server. Start by looking at the static
 * main() method at the bottom of this file.
 */
public class VerySimpleServer extends Thread {
	private ServerSocket serverSock = null;

	public VerySimpleServer(int serverPort) {

		try {
			serverSock = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	// All this method does is wait for some bytes from the
	// connection, read them, then write them back again, until the
	// socket is closed from the other side.
	public void handleConnection(InputStream sockInput, OutputStream sockOutput) {
		if (sockOutput == null) {
			return;
		}

		int count = 0;

		// Wait for 2 seconds to let the testacase start before we start sending
		// events
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// Ignored
		}

		while (true) {
			byte[] buf = new byte[1024];
			int bytes_read = 0;
			try {
				count++;
				// This call to read() will wait forever, until the
				// program on the other side either sends some data,
				// or closes the socket.
				String tmp = Integer.toString(count);
				// System.err.println(count);
				sockOutput.write(tmp.getBytes(), 0, tmp.getBytes().length);
				// This call to flush() is optional - we're saying go
				// ahead and send the data now instead of buffering
				// it.
				sockOutput.flush();

				Thread.sleep(1000);
			} catch (Exception e) {
				System.err
						.println("Exception reading from/writing to socket, e="
								+ e);
				e.printStackTrace(System.err);
				return;
			}
		}

	}

	public void run() {
		Socket sock = null;
		InputStream sockInput = null;
		OutputStream sockOutput = null;
		while (true) {
			try {
				// This method call, accept(), blocks and waits
				// (forever if necessary) until some other program
				// opens a socket connection to our server. When some
				// other program opens a connection to our server,
				// accept() creates a new socket to represent that
				// connection and returns.
				System.out.println("Waiting for new connection");
				sock = serverSock.accept();
				System.out.println("Have accepted new socket.");

				// From this point on, no new socket connections can
				// be made to our server until we call accept() again.

				sockInput = sock.getInputStream();
				sockOutput = sock.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}

			// Do something with the socket - read bytes from the
			// socket and write them back to the socket until the
			// other side closes the connection.
			handleConnection(sockInput, sockOutput);

			// Now we close the socket.
			if (sock != null) {
				System.err.println("Closing socket.");
				try {
					sock.close();
				} catch (Exception e) {
					System.err.println("Exception while closing socket.");
					e.printStackTrace(System.err);
				}
			}
			System.err
					.println("Finished with socket, waiting for next connection.");
		}
	}

}
