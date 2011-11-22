package com.hqsolution.hqserver.client.processor;


import org.jpos.iso.ISOMsg;

import com.hqsolution.hqserver.app.common.EntityType;
import com.hqsolution.hqserver.app.common.TaskCodeDefinition;
import com.hqsolution.hqserver.app.dto.BankAccount;
import com.hqsolution.hqserver.app.dto.FlexibleTask;
import com.hqsolution.hqserver.app.pack.ObjectPackMessage;
import com.hqsolution.hqserver.client.factory.IsoMessageBuilder;

public class BankAccountPersistProcessor extends RequestProcessor{
	private BankAccount account;
	
	public BankAccountPersistProcessor(BankAccount account) {
		this.account = account;
	}
	
	@Override
	public void process() {
		FlexibleTask flexibleTask = new FlexibleTask(
				TaskCodeDefinition.ADD_BANK_ACCOUNT, account);
		ObjectPackMessage message = new ObjectPackMessage(flexibleTask,
				EntityType.FLEXIBLE_TASK);
		// for field 11
		byte[] data = message.pack();
		IsoMessageBuilder.createBuilder().rebuild(this.msgSent)
				.setField48(data).build();
		super.process();
	}
	
	public BankAccount getBankAccount() {
		return account;
	}
}
