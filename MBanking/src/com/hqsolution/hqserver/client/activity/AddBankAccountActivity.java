package com.hqsolution.hqserver.client.activity;

import com.hqsolution.hqserver.client.listener.OnClickAddBankAccount;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddBankAccountActivity extends Activity{
	private EditText acountName;
	private EditText accountNumber;
	private EditText routingNumber;
	private EditText bankName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_bank_account_screen);
		setTitle(R.string.create_account_title);
		
		
		
		Button button = (Button)findViewById(R.id.btn_add_bank_account);
		button.setOnClickListener(new OnClickAddBankAccount(this));
	}
}
