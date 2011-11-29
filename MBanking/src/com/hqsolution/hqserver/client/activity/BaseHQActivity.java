package com.hqsolution.hqserver.client.activity;

import com.hqsolution.hqserver.client.data.helper.ApplicationDataHelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
/**
 * Base Activity 
 * @author QuanLe
 *
 */
public class BaseHQActivity extends Activity {
	
	protected ApplicationDataHelper dataHelper;
	protected ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		dataHelper = new ApplicationDataHelper(this);
		
	}
	
	public ApplicationDataHelper getDataHelper() {
		return dataHelper;
	}
	
	public ProgressDialog showProgressDialog(String message) {
		progressDialog =  ProgressDialog.show(this, "Waiting", message);
		return progressDialog;
	}
	
	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		dataHelper.close();
	}
}
