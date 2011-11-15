package com.hqsolution.hqserver.client.listener;

import com.hqsolution.hqserver.client.activity.CreateAccountActivity;
import com.hqsolution.hqserver.client.activity.MainScreenActivity;
import com.hqsolution.hqserver.client.activity.MobilePaymentActivity;

import android.content.Intent;
import android.view.View;

public class OnLoginClickListener implements View.OnClickListener{

	private MobilePaymentActivity activity;
	
	public OnLoginClickListener(MobilePaymentActivity activity) {
		this.activity = activity;
	}
	
	public void onClick(View arg0) {
		Intent i = new Intent(activity, MainScreenActivity.class);
        activity.startActivityForResult(i, 2);
	}
	
	
}
