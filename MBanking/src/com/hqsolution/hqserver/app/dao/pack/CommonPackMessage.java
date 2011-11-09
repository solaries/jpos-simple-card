package com.hqsolution.hqserver.app.dao.pack;


import java.util.List;

import com.hqsolution.hqserver.app.dao.util.MessageUtil;

public class CommonPackMessage{
	
	protected byte[] data = {};

	public CommonPackMessage() {
		process();
	}

	public CommonPackMessage(byte[] data) {
		this.data = data;
		process();
	}

	public byte[] pack() {
		return data;
	}


	protected void process() {
		int dataLen = data.length;
		byte[] lenBytes = MessageUtil.bindToHead(
				MessageUtil.intToByteArray(dataLen), data);
		this.data = lenBytes;
	}
}
