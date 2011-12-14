package com.hqsolution.hqserver.client.listener;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.client.activity.LoginActivity;
import com.hqsolution.hqserver.client.activity.MainScreenActivity;
import com.hqsolution.hqserver.client.activity.R;
import com.hqsolution.hqserver.client.app.util.AppUtil;
import com.hqsolution.hqserver.client.app.util.HQUserUtility;
import com.hqsolution.hqserver.client.sess.ApplicationSession;
import com.hqsolution.hqserver.client.sess.DefaultSessionListener;

/**
 * 
 * @author QuanLe
 *
 */
public class OnLoginClickListener extends BaseClickListener {

	private LoginActivity activity;
	private boolean isLogin = false;

	public OnLoginClickListener(LoginActivity activity) {
		this.activity = activity;
	}
	
	public void run() {
		
		
	}

	public void onClick(View arg0) {
		activity.showProgressDialog("Login ...");
		try {
			EditText username = (EditText) activity
					.findViewById(R.id.username_login);
			username.setText("hungquan@gmail.com");
			EditText password = (EditText) activity
					.findViewById(R.id.password_login);
			password.setText("123456");
			HQAccount acc = activity.getDataHelper().login(username.getText().toString(),password.getText().toString());
			if (acc != null) {
				activity.getProgressDialog().dismiss();
				ApplicationSession.getInstance().addSessionLifeCycleListener(new DefaultSessionListener());
				ApplicationSession.getInstance().set(HQAccount.BIND_NAME, acc);
				Intent i = new Intent(activity, MainScreenActivity.class);
				activity.startActivityForResult(i, 2);
			}else{
				activity.getProgressDialog().dismiss();
				AppUtil.createMessageDialog(activity,"Your username and password is error").show();
			}
			
		} catch (Exception e) {
			activity.getProgressDialog().dismiss();
			AppUtil.createExitOnErrorDialog(activity, e).show();
			throw new RuntimeException(e);
		}
	}

}
