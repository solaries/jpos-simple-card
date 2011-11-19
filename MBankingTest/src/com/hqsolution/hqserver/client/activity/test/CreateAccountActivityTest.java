/**
 * 
 */
package com.hqsolution.hqserver.client.activity.test;

import android.test.ActivityInstrumentationTestCase2;

import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.client.activity.CreateAccountActivity;
import com.hqsolution.hqserver.client.channel.NACChannel;
import com.hqsolution.hqserver.client.processor.AccountCreateProcessor;

/**
 * @author HUNGPT
 *
 */
public class CreateAccountActivityTest extends
		ActivityInstrumentationTestCase2<CreateAccountActivity> {

	public CreateAccountActivityTest() {
		super("com.hqsolution.hqserver.client.activity",CreateAccountActivity.class);
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.hqsolution.hqserver.client.activity.CreateAccountActivity#save(com.hqsolution.hqserver.app.dto.HQAccount)}.
	 */
	public void testSave() {
		/*HQAccount login = new HQAccount();
		login.setEmail("lmquan008@gmail.com");
		login.setPassword("1234566");
		login.setFullName("Le Minh Quan");
		
		AccountCreateProcessor processor = new AccountCreateProcessor(login);
		processor.process();*/
		
		NACChannel.main(new String[]{});
	}

}
