package com.hqsolution.hqserver.client.listener;

import com.hqsolution.hqserver.client.activity.BaseHQActivity;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.ProgressBar;

public abstract class BaseClickListener implements View.OnClickListener, Runnable {
	
	protected void startThread(){
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		// TODO Auto-generated method stub
		
	}

	

}
