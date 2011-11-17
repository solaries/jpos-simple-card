package com.hqsolution.hqserver.client.test;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hqsolution.hqserver.app.dto.AccountLogin;
import com.hqsolution.hqserver.client.processor.AccountCreateProcessor;

public class TestSendingMessage extends TestCase {
	
	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("Run " + TestMessage.class.getName() + " .");
	}

	@AfterClass
	public static void runAfterClass() {
		System.out.println("End " + TestMessage.class.getName() + " .");
	}
	
	@Test
	public void testSendingMessage(){
		AccountLogin login = new AccountLogin();
		login.setEmail("lmquan008@gmail.com");
		login.setPassword("1234566");
		login.setFullname("Le Minh Quan");
		
		AccountCreateProcessor processor = new AccountCreateProcessor(login);
		processor.process();
	}
}
