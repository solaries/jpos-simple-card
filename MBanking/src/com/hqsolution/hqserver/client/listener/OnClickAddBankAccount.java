package com.hqsolution.hqserver.client.listener;

import com.hqsolution.hqserver.app.dto.BankAccount;
import com.hqsolution.hqserver.client.activity.AddBankAccountActivity;
import com.hqsolution.hqserver.client.activity.R;

import android.view.View;
import android.widget.EditText;

public class OnClickAddBankAccount extends BaseClickListener {

	private AddBankAccountActivity activity;
	
	public OnClickAddBankAccount(AddBankAccountActivity activity){
		this.activity = activity;
	}
	
	public void onClick(View arg0) {
		EditText txtAccountHolder = (EditText)activity.findViewById(R.id.txtAccountHolder);
		EditText txtAccountNumber = (EditText)activity.findViewById(R.id.txtAccountNumber);
		EditText txtRoutingNumber = (EditText)activity.findViewById(R.id.txtRoutingNumber);
		EditText txtBankName = (EditText)activity.findViewById(R.id.txtBankName);
		
		BankAccount account = new BankAccount(txtBankName.getText().toString(),
				txtRoutingNumber.getText().toString(),
				txtAccountNumber.getText().toString(),
				txtAccountHolder.getText().toString());
	}

}
