package com.hqsolution.hqserver.app.common;

import com.hqsolution.hqserver.app.dto.BankAccount;
import com.hqsolution.hqserver.app.dto.HQAccount;

/**
 * This is used to determine what to do at server
 * 
 * @author QuanLe
 *
 */

public class TaskCodeDefinition {
	/**
	 * Cast to {@link HQAccount}
	 */
	public static String ADD_ACCOUNT = "ADD_ACCOUNT";
	/**
	 * Cast to {@link HQAccount}
	 */
	public static String GET_ACCOUNT = "GET_ACCOUNT";
	
	/**
	 * Cast to {@link HQAccount}
	 */
	public static String UPDATE_ACCOUNT = "UPDATE_ACCOUNT";
	
	/**
	 * Cast to {@link BankAccount}
	 */
	public static String ADD_BANK_ACCOUNT = "ADD_BANK_ACCOUNT";
	
	
}
