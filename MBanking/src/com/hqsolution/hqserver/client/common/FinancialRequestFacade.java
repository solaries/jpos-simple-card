package com.hqsolution.hqserver.client.common;


import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.app.dto.BankAccount;
import com.hqsolution.hqserver.client.factory.IsoMessageBuilder;
import com.hqsolution.hqserver.client.processor.AccountCreateProcessor;

/**
 * Implements the methods that make request to server
 * @author Quan
 *
 */
public class FinancialRequestFacade {
	private static FinancialRequest instance;
	
	public static FinancialRequest getInstance(){
		if(instance == null){
			return new PhoneRequestImpl();
		}
		return instance;
	}
	
	public static final class PhoneRequestImpl implements FinancialRequest{
		
		
		PhoneRequestImpl(){
			
		}
		
		public boolean saveUserInfo(HQAccount account) {
			AccountCreateProcessor processor = new AccountCreateProcessor(account);
			processor.process();
			return false;
		}
	}
}
