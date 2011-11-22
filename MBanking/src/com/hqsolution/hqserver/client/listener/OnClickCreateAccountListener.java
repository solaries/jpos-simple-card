package com.hqsolution.hqserver.client.listener;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.ProgressBar;

import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.client.activity.CreateAccountActivity;
import com.hqsolution.hqserver.client.app.util.AppUtil;
import com.hqsolution.hqserver.client.common.FinancialRequest;

public class OnClickCreateAccountListener extends BaseClickListener{

	private CreateAccountActivity activity;

	public OnClickCreateAccountListener(CreateAccountActivity activity) {
		super();
		this.activity = activity;
	}

	/**
	 * run Asyn
	 */
	public void run() {
		try {
			FinancialRequest facade = FinancialRequest.Factory.newInstance();
			HQAccount login = new HQAccount();
			login.setFullName(activity.getName().getText().toString());
			login.setEmail(activity.getEmail().getText().toString());
			login.setPassword(activity.getPassword().getText().toString());
			/*HQAccount login = new HQAccount();
			login.setEmail("lmquan008@gmail.com");
			login.setPassword("1234566");
			login.setFullName("Le Minh Quan");*/
			
			if(facade.saveUserInfo(login)){
				//save user info into phone
				activity.save(login);
			}
			activity.save(login);
			// close dialog
			activity.getProgressDialog().dismiss();
			activity.finish();
		} catch (Exception e) {
			e.printStackTrace();
			activity.getProgressDialog().dismiss();
			AppUtil.createExitOnErrorDialog(activity, e).show();

		}
	}

	public void onClick(View v) {
		
	}
}
