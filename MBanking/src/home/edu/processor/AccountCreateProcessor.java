package home.edu.processor;

import home.edu.factory.IsoMessageBuilder;
import home.edu.msg.pack.ObjectPackMessage;

import org.jpos.iso.ISOMsg;

import com.hqsolution.hqserver.app.dao.AccountLogin;
import com.hqsolution.hqserver.app.dao.EntityType;

public class AccountCreateProcessor extends RequestProcessor {

	public AccountCreateProcessor(AccountLogin accountLogin) {
		super();
		ObjectPackMessage message = new ObjectPackMessage(accountLogin,
				EntityType.ACCOUNT_LOGIN);
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
