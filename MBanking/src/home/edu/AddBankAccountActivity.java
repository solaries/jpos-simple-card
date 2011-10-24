package home.edu;

import android.app.Activity;
import android.os.Bundle;

public class AddBankAccountActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_bank_account_screen);
		setTitle(R.string.create_account_title);
	}
}
