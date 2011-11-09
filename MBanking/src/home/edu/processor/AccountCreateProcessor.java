package home.edu.processor;

import home.edu.factory.IsoMessageBuilder;

import org.jpos.iso.ISOMsg;

import com.hqsolution.hqserver.app.dao.AccountLogin;
import com.hqsolution.hqserver.app.dao.EntityType;
import com.hqsolution.hqserver.app.dao.FlexibleTask;
import com.hqsolution.hqserver.app.dao.TaskCodeDefinition;
import com.hqsolution.hqserver.app.dao.pack.ObjectPackMessage;

public class AccountCreateProcessor extends RequestProcessor {

	public AccountCreateProcessor(AccountLogin accountLogin) {
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
