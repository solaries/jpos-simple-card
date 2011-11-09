package home.edu;

import android.app.Activity;
import android.os.Bundle;

/**
 * Create new account for login
 * 
 * @author Quan
 *
 */
public class CreateAccountActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_account_screen);
		setTitle(R.string.create_account_title);
		
	}
}
