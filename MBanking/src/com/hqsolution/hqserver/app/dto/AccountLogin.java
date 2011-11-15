package com.hqsolution.hqserver.app.dto;

import java.io.Serializable;


/**
 * 
 * Simulate an account login with username and password
 * @author Quan
 *
 */
public class AccountLogin implements Serializable{ 
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3291841779931161962L;
	private String password;
	private String fullname;
	private String email;
	
	public AccountLogin() {
		
	}
	

	public String getPassword() {
		return password;
	}

	public String getFullname() {
		return fullname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		AccountLogin other = (AccountLogin) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}
