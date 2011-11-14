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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
		+ ((accountId == null) ? 0 : accountId.hashCode());

		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}
