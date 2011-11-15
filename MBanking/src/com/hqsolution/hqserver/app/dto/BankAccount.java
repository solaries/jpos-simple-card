package com.hqsolution.hqserver.app.dto;


import java.io.IOException;
import java.io.Serializable;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import com.hqsolution.hqserver.app.util.MessageUtil;

/**
 * 
 * @author Quan
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
	
	
}
