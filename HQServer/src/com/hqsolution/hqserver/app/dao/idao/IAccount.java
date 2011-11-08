package com.hqsolution.hqserver.app.dao.idao;

import com.hqsolution.hqserver.app.dto.Account;

/**
 * 
 * @author HUNGPT
 *
 */
public interface IAccount {
	/**
	 * Create a new account in system.
	 * @param acct account what we do need to insert to DB
	 * @return new accountId
	 */
	public String createAccount(Account acct);
	
	/**
	 * Remove out an account.
	 * @param accountId account id need to be removed
	 * @return status of delete account statement
	 */
	public int deleteAccount(String accountId);
	
	/**
	 * Close out an account.
	 * @param accountId account Id of account needs to be closed out
	 * @return status of close account statement
	 */
	public int closeAccount(String accountId);
	
	/**
	 * Open an account if this account has been close previous.
	 * @param accountId
	 * @return
	 */
	public int openAccount(String accountId);
}
