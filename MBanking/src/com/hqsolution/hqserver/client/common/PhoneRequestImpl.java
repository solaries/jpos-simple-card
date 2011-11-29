package com.hqsolution.hqserver.client.common;

import org.apache.commons.codec.binary.StringUtils;

import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.app.dto.BankAccount;
import com.hqsolution.hqserver.client.factory.IsoMessageBuilder;
import com.hqsolution.hqserver.client.processor.HQAccountCreateProcessor;
import com.hqsolution.hqserver.client.processor.LoginActionProcessor;

/**
 * Implements the methods that make request to server
 * 
 * @author Quan
 * 
 */

public final class PhoneRequestImpl implements FinancialRequest {

	PhoneRequestImpl() {

	}

	public boolean saveUserInfo(HQAccount account) {
		/*HQAccountCreateProcessor processor = new HQAccountCreateProcessor(
				account);
		processor.process();*/

		return true;
	}

	public HQAccount login(String username, String password) {
		/*HQAccount accountLogin = new HQAccount();
		accountLogin.setEmail(username);
		accountLogin.setPassword(password);
		LoginActionProcessor processor = new LoginActionProcessor(accountLogin);
		processor.process();
		HQAccount returnAccount = processor.getHQAccount();
		if (returnAccount.getFullName() == null
				|| returnAccount.getFullName().trim().length() == 0) {
			return returnAccount;
		}
		return null;*/
		HQAccount accountLogin = new HQAccount();
		accountLogin.setFullName("Quan Minh Le");
		accountLogin.setEmail("lmquan008@yahoo.com");
		accountLogin.setPassword("123456");
		return accountLogin;
	}
}
