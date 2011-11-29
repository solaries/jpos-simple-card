package com.hqsolution.hqserver.client.activity;

import com.hqsolution.hqserver.client.listener.OnClickAddBankAccount;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class AddBankAccountActivity extends Activity{
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
