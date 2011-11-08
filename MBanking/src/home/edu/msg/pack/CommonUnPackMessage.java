package home.edu.msg.pack;

import home.edu.msg.util.MessageUtil;

import java.util.List;

public class CommonUnPackMessage{
	
	protected byte[] data = {};

	
	public CommonUnPackMessage(byte[] data) {
		this.data = data;
	}

	public byte[] unpack() {
		List<byte[]> list = MessageUtil.cutByteArray(data, 2);
		return list.get(1);
	}

}
