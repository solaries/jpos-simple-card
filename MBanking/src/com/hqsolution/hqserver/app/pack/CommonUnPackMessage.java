package com.hqsolution.hqserver.app.pack;


import java.util.List;

import com.hqsolution.hqserver.app.util.MessageUtil;

public class CommonUnPackMessage{
	
	protected byte[] data = {};
	private int messageLength;
	private byte[] unpackData; 

	
	public CommonUnPackMessage(byte[] data) {
		this.data = data;
	}

	/**
	 * 
	 * @return
	 */
	public byte[] unpack() {
		List<byte[]> list = MessageUtil.cutByteArray(data, 4);
		messageLength = list.get(1).length;
		unpackData =  list.get(1);
		return unpackData;
	}
	
	public int getMessageLength() {
		return messageLength;
	}
	
	public byte[] getUnpackData() {
		return unpackData;
	}

}
