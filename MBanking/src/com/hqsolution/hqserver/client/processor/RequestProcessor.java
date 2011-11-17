package com.hqsolution.hqserver.client.processor;


import java.io.IOException;
import java.util.Date;

import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.channel.ASCIIChannel;

import com.hqsolution.hqserver.app.util.MessageUtil;
import com.hqsolution.hqserver.client.factory.IsoMessageBuilder;
import com.hqsolution.hqserver.client.factory.PackagerFactory;

public abstract class RequestProcessor {
	private static final String SERVER = "localhost";
	private static final int PORT = 65000;

	protected ISOMsg msgSent;
	protected ISOMsg msgReceived;
	private byte[] field3;
	private byte[] field11;
	private byte[] field48;

	public RequestProcessor() {
		init();
	}
	
	private void init(){
		ISOPackager pack = PackagerFactory.getPackager();
		this.msgSent = IsoMessageBuilder.createBuilder()
				.setMTI("0200")
				.setField3("000000")
				.setField11(new Date())
				.build();
	}

	public ISOMsg getMsgReceived() {
		return msgReceived;
	}

	public void process() {
		try {
			ISOChannel channel = new ASCIIChannel(SERVER, PORT, this.msgSent.getPackager());
			channel.connect();
			channel.send(msgSent);
			msgReceived = channel.receive();
			channel.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		field3 = msgReceived.getBytes(3);

		field11 = msgReceived.getBytes(11);

		field48 = msgReceived.getBytes(48);
	}


}
