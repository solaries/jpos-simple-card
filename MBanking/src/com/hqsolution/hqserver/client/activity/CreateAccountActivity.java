package com.hqsolution.hqserver.client.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.hqsolution.hqserver.app.dto.HQAccount;
import com.hqsolution.hqserver.client.data.helper.ApplicationDataHelper;
import com.hqsolution.hqserver.client.listener.OnClickCreateAccountListener;

/**
 * Create new account for login
 * 
 * @author Quan
 * 
 */
public class CreateAccountActivity extends Activity {

	private EditText name = null;
	private EditText password = null;
	private EditText email = null;
	private ProgressDialog progressDialog;
	ApplicationDataHelper dataHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		dataHelper = new ApplicationDataHelper(this);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.create_account_screen);
		setTitle(R.string.create_account_title);
		name = (EditText) this.findViewById(R.id.txtName);
		password = (EditText) this.findViewById(R.id.txtPassword);
		email = (EditText) this.findViewById(R.id.txtEmail);
		
		Button but = (Button)this.findViewById(R.id.btnCreateAccount);
		but.setOnClickListener(new OnClickCreateAccountListener(this));
	}

	public EditText getName() {
		return name;
	}

	public EditText getPassword() {
		return password;
	}

	public EditText getEmail() {
		return email;
	}
	
	public ProgressDialog showProgressDialog() {
		progressDialog =  ProgressDialog.show(this, "Waiting", "Please wait saving account ...");
		return progressDialog;
	}
	
	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}
	
	public void save(HQAccount accountLogin){
		this.dataHelper.insertAccountLogin(accountLogin);
	}
	
	@Override
	protected void onDestroy() {
		dataHelper.close();
		super.onDestroy();
	}

}
