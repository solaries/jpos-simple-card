package com.hqsolution.hqserver.client.activity;

import com.hqsolution.hqserver.client.data.helper.ApplicationDataHelper;

import home.edu.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Create new account for login
 * 
 * @author Quan
 * 
 */
public class CreateAccountActivity extends Activity {

	EditText name = (EditText) this.findViewById(R.id.txtName);
	EditText password = (EditText) this.findViewById(R.id.txtPassword);
	EditText email = (EditText) this.findViewById(R.id.txtEmail);
	private ProgressDialog progressDialog;
	ApplicationDataHelper dataHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		dataHelper = new ApplicationDataHelper(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_account_screen);
		setTitle(R.string.create_account_title);

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
		return ProgressDialog.show(this, "Waiting", "Please wait saving account ...");
	}
	
	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}
	
	@Override
	protected void onDestroy() {
		dataHelper.close();
		super.onDestroy();
	}

}
