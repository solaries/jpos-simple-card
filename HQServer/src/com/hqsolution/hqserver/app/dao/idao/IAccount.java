package com.hqsolution.hqserver.app.dao.idao;

import com.hqsolution.hqserver.app.common.DatabaseConnection;
import com.hqsolution.hqserver.app.dto.HQAccount;

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
	public String createAccount(HQAccount acct);
	
	/**
	 * Create a new account in system.
	 * @param conn Connection connected to DB
	 * @param acct account what we do need to insert to DB
	 * @return new accountId
	 */
	public String createAccount(DatabaseConnection conn, HQAccount acct);
	
	/**
	 * Remove out an account.
	 * @param accountId account id need to be removed
	 * @return status of delete account statement
	 */
	public int deleteAccount(String accountId);
	
	/**
	 * Remove out an account.
	 * @param conn Connection connected to DB
	 * @param accountId account id need to be removed
	 * @return status of delete account statement
	 */
	public int deleteAccount(DatabaseConnection conn, String accountId);
	
	/**
	 * Close out an account.
	 * @param accountId account Id of account needs to be closed out
	 * @return status of close account statement
	 */
	public int closeAccount(String accountId);
	
	/**
	 * Close out an account.
	 * @param conn Connection connected to DB
	 * @param accountId account Id of account needs to be closed out
	 * @return status of close account statement
	 */
	public int closeAccount(DatabaseConnection conn, String accountId);
	
	/**
	 * Open an account if this account has been close previous.
	 * @param accountId
	 * @return
	 */
	public int openAccount(String accountId);
	
	/**
	 * Open an account if this account has been close previous.
	 * @param conn Connection connected to DB
	 * @param accountId
	 * @return
	 */
	public int openAccount(DatabaseConnection conn, String accountId);
}
