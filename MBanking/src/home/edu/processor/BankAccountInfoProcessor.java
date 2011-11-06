package home.edu.processor;

import home.edu.entiy.BankAccount;

import org.jpos.iso.ISOMsg;

public class BankAccountInfoProcessor extends RequestProcessor{
	
	//indicate saved action is OK
	private Boolean persistOk;
	
	public BankAccountInfoProcessor(ISOMsg isoMsg) {
		super(isoMsg);
	}
	
	@Override
	public void process() {
		super.process();
		ISOMsg isoMsg = getMsgReceived();
		
		//get information from message
		
		persistOk = true;
	}
	
	
	public Boolean isPersistOk() {
		return persistOk;
	}
}
