package home.edu.pack;

import home.edu.util.AppUtil;
import home.edu.util.MessageUtil;

public class CommonMessage{
	protected byte[] data = {};
	
	
	public CommonMessage() {
		// TODO Auto-generated constructor stub
	}
	
	public CommonMessage(byte[] data) {
		this.data = data;
	}
	
	protected void process() {
		long dataLen = data.length;
		byte[] lenBytes = MessageUtil.bindToHead(AppUtil.longToByteArray(dataLen), data);
		this.data = lenBytes;
	}
}
