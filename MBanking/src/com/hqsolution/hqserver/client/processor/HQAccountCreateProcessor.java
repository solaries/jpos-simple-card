package com.hqsolution.hqserver.client.processor;


import org.jpos.iso.ISOMsg;

import com.hqsolution.hqserver.app.common.EntityType;
import com.hqsolution.hqserver.app.common.TaskCodeDefinition;
import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.app.dto.FlexibleTask;
import com.hqsolution.hqserver.app.pack.ObjectPackMessage;
import com.hqsolution.hqserver.client.factory.IsoMessageBuilder;

public class HQAccountCreateProcessor extends RequestProcessor {

	public HQAccountCreateProcessor(HQAccount accountLogin) {
		super();
		FlexibleTask flexibleTask = new FlexibleTask(
				TaskCodeDefinition.ADD_ACCOUNT, accountLogin);
		ObjectPackMessage message = new ObjectPackMessage(flexibleTask,
				EntityType.FLEXIBLE_TASK);
		// for field 11
		byte[] data = message.pack();
		IsoMessageBuilder.createBuilder().rebuild(this.msgSent)
				.setField48(data).build();
	}

	@Override
	public void process() {
		super.process();
		ISOMsg receiveMessage = getMsgReceived();
	}
}
