package com.hqsolution.hqserver.app.pack;


import com.hqsolution.hqserver.app.util.MessageUtil;

/**
 * 
 * @author Quan Le
 *
 */
public class CommonPackMessage{
	
	protected byte[] data = {};

	public CommonPackMessage() {
		process();
	}

	public CommonPackMessage(byte[] data) {
		this.data = data;
		process();
	}

	/**
	 * Packing process is executed when constructing <code>CommonPackMessage</code>
	 * @return data after packing.
	 */
	public byte[] pack() {
		return data;
	}

	/**
	 * Attach a data length to ahead of data.
	 * return data = data_length + data
	 */
	protected void process() {
		int dataLen = data.length;
		byte[] lenBytes = MessageUtil.bindToHead(
				MessageUtil.intToByteArray(dataLen), data);
		this.data = lenBytes;
	}
}
