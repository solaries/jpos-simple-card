package com.hqsolution.hqserver.client.common;

import com.hqsolution.hqserver.app.dto.AccountLogin;
import com.hqsolution.hqserver.app.dto.BankAccount;

/**
 * 
 * Define methods that make requests to server
 * @author Quan
 *
 */
public interface FinancialRequest {
	boolean saveUserInfo(AccountLogin accountLogin);
}
