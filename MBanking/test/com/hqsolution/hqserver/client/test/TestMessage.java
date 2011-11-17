package com.hqsolution.hqserver.client.test;

import java.io.IOException;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hqsolution.hqserver.app.common.EntityType;
import com.hqsolution.hqserver.app.common.TaskCodeDefinition;
import com.hqsolution.hqserver.app.dto.AccountLogin;
import com.hqsolution.hqserver.app.dto.FlexibleTask;
import com.hqsolution.hqserver.app.pack.ObjectPackMessage;
import com.hqsolution.hqserver.app.pack.ObjectUnPackMessage;
import com.hqsolution.hqserver.app.util.MessageUtil;

public class TestMessage extends TestCase{
	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Run " + TestMessage.class.getName() + " .");
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("End " + TestMessage.class.getName() + " .");
	}

	@Test
	public void testObjectPackMessage() throws IOException {
		AccountLogin login = new AccountLogin();
		login.setEmail("lmquan008@gmail.com");
		login.setPassword("1234566");
		login.setFullname("Le Minh Quan");
		
		FlexibleTask flexibleTask = new FlexibleTask(TaskCodeDefinition.ADD_ACCOUNT, login);
		byte[] objectBytes = MessageUtil.convertObjectToByteArray(flexibleTask);
		
		ObjectPackMessage msg = new ObjectPackMessage(flexibleTask, EntityType.FLEXIBLE_TASK);
		byte[] data = msg.pack();
		
		ObjectUnPackMessage unPackMessage = new ObjectUnPackMessage(data);
		Object newObj = unPackMessage.unpackObject();
		
		assertEquals(flexibleTask, newObj);
	}
	
	@Test
	public void testDatetoLong() {
		Date date = new Date();
		byte[] b = MessageUtil.dateToSixBytes(date);
		System.out.println(b);
		Date date1 = MessageUtil.sixBytesToDate(b);
		System.out.println(date);
		assertEquals(date, date1);
	}
}
