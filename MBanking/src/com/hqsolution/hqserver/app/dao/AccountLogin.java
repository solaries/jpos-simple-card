package com.hqsolution.hqserver.app.dao;

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
}
