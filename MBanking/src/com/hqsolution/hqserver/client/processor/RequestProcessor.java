package com.hqsolution.hqserver.client.processor;


import java.io.IOException;
import java.util.Date;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;

import com.hqsolution.hqserver.app.util.MessageUtil;
import com.hqsolution.hqserver.client.channel.NACChannel;
import com.hqsolution.hqserver.client.factory.IsoMessageBuilder;
import com.hqsolution.hqserver.client.factory.PackagerFactory;

public abstract class RequestProcessor {

	protected ISOMsg msgSent;
	protected ISOMsg msgReceived;
	private byte[] field39;
	private byte[] field48;
	
	/** Header is just meaning for NACChannel. Header is used for router. */
	byte[] header = new byte[] {0x60, 0x00, 0x01, 0x00, 0x02 };
	
	/** Packager is tied up to ISOChannel.
	 *  We should not tie up ISOPakager to ISOMsg. 
	 **/  
	ISOPackager packager = null;

	public RequestProcessor() {
		init();
	}
	
	private void init(){
		packager = PackagerFactory.getPackager();
		this.msgSent = IsoMessageBuilder.createBuilder()
				.setMTI("0200")
				.setField3("000000")
				.setField11(new Date())
				.build();
		msgSent.setPackager(packager);
	}

	public ISOMsg getMsgReceived() {
		return msgReceived;
	}

	public void process() {
		try {
			NACChannel channel = new NACChannel(packager, header);
			channel.connect();
			
			channel.send(msgSent);
			msgReceived = channel.receive();
			
			//testing only
			//System.out.println(ISOUtil.hexString(msgReceived.pack()));
			
			channel.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(msgReceived != null){
			throw new RuntimeException("Cannot receive message from server");
		}
	}

	public byte[] getReceiveField39() {
		if(field39 != null) return field39;
		return msgReceived.getBytes(39);
		
	}


	public byte[] getReceiveField48() {
		if(field48 != null) return field48;
		return msgReceived.getBytes(48);
	}

}
