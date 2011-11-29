package com.hqsolution.hqserver.app.dto;


import java.io.Serializable;

/**
 * 
 * @author QuanLe
 *
 */
public class BankAccount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6753967775904068032L;
	private String bankName;
	private String routingNumber;
	private String accountNumber;
	private String fullName;
	private boolean verify;
	private boolean valid;
	
	public BankAccount() {
		// TODO Auto-generated constructor stub
	}

	public BankAccount(String bankName, String routingNumber,
			String accountNumber, String fullName) {
		super();
		this.bankName = bankName;
		this.routingNumber = routingNumber;
		this.accountNumber = accountNumber;
		this.fullName = fullName;
		this.verify = false;
		this.valid = false;
	}

	public String getBankName() {
		return bankName;
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	public String getFullName() {
		return fullName;
	}
	
	public boolean isVerify() {
		return verify;
	}
	
	public boolean isValid() {
		return valid;
	}
	
}
