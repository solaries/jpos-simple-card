package home.edu.msg.pack;

import home.edu.msg.util.MessageUtil;

import java.util.List;

public class CommonPackMessage{
	
	protected byte[] data = {};

	public CommonPackMessage() {
		process();
	}

	public CommonPackMessage(byte[] data) {
		this.data = data;
		process();
	}

	public CommonPackMessage(byte[] data, boolean unpack) {
		this.data = data;
		if (!unpack) {
			process();
		}

	}

	public byte[] pack() {
		return data;
	}

	public byte[] unpack() {
		List<byte[]> list = MessageUtil.cutByteArray(data, 2);
		return list.get(1);
	}

	protected void process() {
		long dataLen = data.length;
		byte[] lenBytes = MessageUtil.bindToHead(
				MessageUtil.longToByteArray(dataLen), data);
		this.data = lenBytes;
	}
}
