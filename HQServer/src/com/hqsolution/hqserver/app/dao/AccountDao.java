package com.hqsolution.hqserver.app.dao;

import java.sql.SQLException;

import com.hqsolution.hqserver.app.common.DatabaseConnection;
import com.hqsolution.hqserver.app.dao.idao.IAccount;
import com.hqsolution.hqserver.app.dto.Account;
import com.hqsolution.hqserver.util.IdGenerator;
import com.hqsolution.hqserver.util.SQLResult;
import com.mysql.jdbc.PreparedStatement;

public class AccountDao implements IAccount {

	String INSERT_ACCOUNT = "insert into hqdb.account(AccountId,Email,Password,FirstLastName) values(?,?,?,?)";

	@Override
	public int closeAccount(String accountId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int closeAccount(DatabaseConnection conn, String accountId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String createAccount(Account acct) {
		//TODO 
		return null;
	}

	@Override
	public String createAccount(DatabaseConnection conn, Account acct) {
		String accountId = IdGenerator.getId();
		try {
			PreparedStatement insertAccount = (PreparedStatement) conn.prepareStatement(INSERT_ACCOUNT);
			insertAccount.setString(1, accountId);
			insertAccount.setString(2, acct.getEmail());
			insertAccount.setString(3, acct.getPassword());
			insertAccount.setString(4, acct.getFullName());
			int result = insertAccount.executeUpdate();
			if(result <= 0){
				return SQLResult.FAIL;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return SQLResult.SUCCESS;
	}

	@Override
	public int deleteAccount(String accountId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAccount(DatabaseConnection conn, String accountId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int openAccount(String accountId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int openAccount(DatabaseConnection conn, String accountId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
