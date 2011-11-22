package com.hqsolution.hqserver.client.common;

import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.app.dto.BankAccount;

/**
 * 
 * Define methods that make requests to server
 * @author Quan
 *
 */
public interface FinancialRequest {
	
	boolean saveUserInfo(HQAccount account);
	
	HQAccount login(String username, String password);
	
	public static class Factory {
		private static FinancialRequest instance;
		
		public static FinancialRequest newInstance(){
			if(instance == null){
				instance =  new PhoneRequestImpl();
			}
			return instance;
		}
		
	}
}
