package com.hqsolution.hqserver.app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hqsolution.hqserver.app.common.DatabaseConnection;
import com.hqsolution.hqserver.app.dao.idao.IAccount;
import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.util.IdGenerator;
import com.hqsolution.hqserver.util.SQLResult;


public class AccountDao implements IAccount {

	String INSERT_ACCOUNT = "insert into hqdb.account(accountId,email,password,fullName) values(?,?,?,?)";
	String CHECK_ACCOUNT = "select * from hqdb.account where email = ?";

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
	public String createAccount(HQAccount acct) {
		//TODO 
		return null;
	}

	@Override
	public String createAccount(DatabaseConnection conn, HQAccount acct) {
		String accountId = IdGenerator.getId();
		try {
			PreparedStatement insertAccount = (PreparedStatement) conn.prepareStatement(INSERT_ACCOUNT,"InsertAccount");
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
		
		return accountId;
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

	@Override
	public int checkAccount(DatabaseConnection conn, HQAccount account) {
		try {
			PreparedStatement checkStm = (PreparedStatement) conn.prepareStatement(CHECK_ACCOUNT,"CheckAccount");
			checkStm.setString(1, account.getEmail());
			ResultSet rs =  checkStm.executeQuery();
			if(rs.next()){
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
}
