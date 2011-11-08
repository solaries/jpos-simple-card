package com.hqsolution.hqserver.app.dto;

public class Account {
	private String accountId;
	private String email;
	private String password;
	private String fullName;
	
	public Account() {
		accountId = "";
		email = "";
		fullName = "";
		password = "";
	}
	
	public Account(String accountId, String email, String fullName, String password) {
		this.accountId = accountId;
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}
	
	public Account(String email, String fullName, String password) {
		this(null, email, fullName, password);
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
