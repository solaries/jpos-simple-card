package home.edu.processor;

import java.io.IOException;

import home.edu.R;
import home.edu.factory.PackagerFactory;

import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.channel.ASCIIChannel;

public abstract class FinancialRequestProcessor {
	private static final String SERVER = "localhost";
	private static final int PORT = 8080;

	private ISOMsg msgSent;
	private ISOMsg msgReceived;

	public FinancialRequestProcessor(ISOMsg sentMsg) {
		this.msgSent = sentMsg;
	}
	
	public ISOMsg getMsgReceived() {
		return msgReceived;
	}

	public void process() {
		try {
			ISOPackager pack = PackagerFactory.getPackager();
			msgSent.setPackager(pack);
			ISOChannel channel = new ASCIIChannel("localhost", 65000, pack);

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
	}
}
