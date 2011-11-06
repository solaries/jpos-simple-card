package home.edu.processor;

import home.edu.factory.IsoMessageBuilder;

import org.jpos.iso.ISOMsg;

public class AccountCreateProcessor extends RequestProcessor{

	
	
	public AccountCreateProcessor(ISOMsg sentMsg) {
		super(sentMsg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() {
		super.process();
		ISOMsg receiveMessage = getMsgReceived();
		
	}
}
