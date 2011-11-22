package com.hqsolution.hqserver.client.channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

import org.jpos.iso.ISOClientSocketFactory;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOFilter;
import org.jpos.iso.ISOHeader;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.header.BaseHeader;

import com.hqsolution.hqserver.app.common.EntityType;
import com.hqsolution.hqserver.app.common.TaskCodeDefinition;
import com.hqsolution.hqserver.app.dto.FlexibleTask;
import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.app.pack.ObjectPackMessage;
import com.hqsolution.hqserver.client.factory.IsoMessageBuilder;
import com.hqsolution.hqserver.client.factory.PackagerFactory;

public class NACChannel {
	// server IP name
	private String serverIPname = "127.0.0.1";

	// server port number
	private int serverPort = 9800;

	// Socket object for communicating
	private java.net.Socket sock = null;

	// socket output to server
	protected DataOutputStream serverOut;

	// socket input from server
	protected DataInputStream serverIn;

	// header
	private byte[] header = null;

	// Packager
	private ISOPackager packager;

	private int timeout = -1;

	private boolean isConnected = false;

	private static final int MAX_PACKAGE_LENGTH = 100000;

	public NACChannel(String serverIP, int port, ISOPackager packager,
			byte[] header) {
		serverIPname = serverIP;
		serverPort = port;
		this.packager = packager;
		this.header = header;
	}

	/**
	 * @param host
	 *            remote host
	 * @param port
	 *            remote port
	 * @throws IOException
	 *             on error
	 * 
	 *             Use Socket factory if exists. If it is missing create a
	 *             normal socket
	 * @see ISOClientSocketFactory
	 * @return newly created socket
	 */
	protected Socket newSocket(String host, int port) throws IOException {
		Socket s = new Socket();
		if (timeout > 0) {
			s.connect(new InetSocketAddress(host, port), timeout);
			
		} else {
			s.connect(new InetSocketAddress(host, port));
		}
		return s;
	}
	
	public void connect() {
		try {
			// create socket and connect
			sock = newSocket(serverIPname, serverPort);

			// create reader and writer
			serverIn = new DataInputStream(sock.getInputStream());
			serverOut = new DataOutputStream(sock.getOutputStream());

			isConnected = true;

			System.out.println("Connected to Server" + serverIPname + ":"
					+ serverPort);

		} catch (Throwable e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void disconnect() {

		isConnected = false;

		if (serverIn != null) {
			try {
				serverIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (serverOut != null) {
			try {
				serverOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (sock != null) {
			try {
				sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * sends an ISOMsg over the TCP/IP session
	 * 
	 * @param m
	 *            the Message to be sent
	 * @exception IOException
	 * @exception ISOException
	 * @exception ISOFilter.VetoException
	 *                ;
	 */
	public void send(ISOMsg m) throws IOException, ISOException {
		try {
			if (!isConnected())
				throw new ISOException("unconnected ISOChannel");
			m.setDirection(ISOMsg.OUTGOING);
			m.setPackager(packager);
			byte[] b = m.pack();
			synchronized (serverOut) {
				sendMessageLength(b.length + getHeaderLength(m));
				sendMessageHeader(m, b.length);
				sendMessage(b, 0, b.length);
				serverOut.flush();
			}
		} catch (ISOException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw new ISOException("unexpected exception", e);
		}
	}

	/**
	 * Waits and receive an ISOMsg over the TCP/IP session
	 * 
	 * @return the Message received
	 * @exception IOException
	 * @exception ISOException
	 */
	public ISOMsg receive() throws IOException, ISOException {
		byte[] b = null;
		byte[] header = null;

		ISOMsg m = createMsg();

		try {
			if (!isConnected())
				throw new ISOException("unconnected ISOChannel");

			synchronized (serverIn) {
				int len = getMessageLength();
				int hLen = getHeaderLength();

				if (len == -1) {
					if (hLen > 0) {
						header = readHeader(hLen);
					}
					b = streamReceive();
				} else if (len > 0 && len <= MAX_PACKAGE_LENGTH) {
					if (hLen > 0) {
						header = readHeader(hLen);
						len -= header.length;
					}
					b = new byte[len];
					getMessage(b, 0, len);
				} else
					throw new ISOException("receive length " + len
							+ " seems strange - maxPacketLength = "
							+ MAX_PACKAGE_LENGTH);
			}
			m.setPackager(packager);
			m.setHeader(getDynamicHeader(header));
			if (b.length > 0) // Ignore NULL messages
				unpack(m, b);

			m.setDirection(ISOMsg.INCOMING);
		} catch (ISOException e) {
			throw e;
		} catch (EOFException e) {
			disconnect();
			throw e;
		} catch (SocketException e) {
			disconnect();
			throw e;
		} catch (InterruptedIOException e) {
			disconnect();
			throw e;
		} catch (IOException e) {
			disconnect();
			throw e;
		} catch (Exception e) {
			throw new ISOException("unexpected exception", e);
		}
		return m;
	}

	/**
	 * support old factory method name for backward compatibility
	 */
	protected ISOMsg createMsg() {
		return createISOMsg();
	}

	protected ISOMsg createISOMsg() {
		return packager.createISOMsg();
	}

	public boolean isConnected() {
		return isConnected;
	}

	protected void sendMessageHeader(ISOMsg m, int len) throws IOException {
		byte[] h = m.getHeader();
		if (h != null) {
			if (h.length == 5) {
				// always swap src/dest address
				byte[] tmp = new byte[2];
				System.arraycopy(h, 1, tmp, 0, 2);
				System.arraycopy(h, 3, h, 1, 2);
				System.arraycopy(tmp, 0, h, 3, 2);
			}
		} else
			h = header;
		if (h != null)
			serverOut.write(h);
	}

	protected void sendMessageLength(int len) throws IOException {
		serverOut.write(len >> 8);
		serverOut.write(len);
	}

	protected int getMessageLength() throws IOException, ISOException {
		byte[] b = new byte[2];
		serverIn.readFully(b, 0, 2);
		return (int) (((((int) b[0]) & 0xFF) << 8) | (((int) b[1]) & 0xFF));
	}

	public void setHeader(String header) {
		this.header = ISOUtil.str2bcd(header, false);
	}

	protected int getHeaderLength() {
		return header != null ? header.length : 0;
	}

	protected int getHeaderLength(ISOMsg m) {
		return (m.getHeader() != null) ? m.getHeader().length
				: getHeaderLength();
	}

	protected void sendMessage(byte[] b, int offset, int len)
			throws IOException {
		serverOut.write(b, offset, len);
	}

	/**
	 * Reads in a message header.
	 * 
	 * @param hLen
	 *            The Length og the reader to read
	 * @return The header bytes that were read in
	 */
	protected byte[] readHeader(int hLen) throws IOException {
		byte[] header = new byte[hLen];
		serverIn.readFully(header, 0, hLen);
		return header;
	}

	protected byte[] streamReceive() throws IOException {
		return new byte[0];
	}

	protected void getMessage(byte[] b, int offset, int len)
			throws IOException, ISOException {
		serverIn.readFully(b, offset, len);
	}

	/**
	 * Allow subclasses to override the Default header on incoming messages.
	 */
	protected ISOHeader getDynamicHeader(byte[] image) {
		return image != null ? new BaseHeader(image) : null;
	}

	protected void unpack(ISOMsg m, byte[] b) throws ISOException {
		m.unpack(b);
	}

	public static void main(String[] args) {
		byte[] header = new byte[] { 0x60, 0x00, 0x01, 0x00, 0x02 };
		NACChannel clientChanel = new NACChannel("20.203.5.190", 9800,
				PackagerFactory.getPackager(), header);
		clientChanel.connect();

		ISOMsg msg = IsoMessageBuilder.createBuilder().setMTI("0200")
				.setField3("000000").setField11(new Date()).build();

		HQAccount login = new HQAccount();
		login.setEmail("lmquan008@gmail.com");
		login.setPassword("1234566");
		login.setFullName("Le Minh Quan");

		FlexibleTask flexibleTask = new FlexibleTask(
				TaskCodeDefinition.ADD_ACCOUNT, login);
		ObjectPackMessage message = new ObjectPackMessage(flexibleTask,
				EntityType.FLEXIBLE_TASK);
		// for field 11
		byte[] data = message.pack();
		IsoMessageBuilder.createBuilder().rebuild(msg).setField48(data).build();

		try {
			// testing only
			//msg.setPackager(PackagerFactory.getPackager());
			//System.out.println(ISOUtil.hexString(msg.pack()));

			clientChanel.send(msg);
			ISOMsg receive = clientChanel.receive();

			// testing only
			//System.out.println(ISOUtil.hexString(receive.pack()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ISOException e) {
			e.printStackTrace();
		}
	}
}
