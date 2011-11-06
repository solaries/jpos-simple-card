package home.edu.processor;

import home.edu.entiy.BankAccount;

import org.jpos.iso.ISOMsg;

public class BankAccountPersistProcessor extends RequestProcessor{
	private BankAccount account;
	
	public BankAccountPersistProcessor(ISOMsg isoMsg) {
		super(isoMsg);
	}
	
	@Override
	public void process() {
		super.process();
		ISOMsg isoMsg = getMsgReceived();
		
		//get information from message
		
		account = null;
	}
	
	public BankAccount getBankAccount() {
		return account;
	}
}
