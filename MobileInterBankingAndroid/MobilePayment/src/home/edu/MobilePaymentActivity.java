package home.edu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * First Activity 
 * 
 * This is the front page of application
 * 
 * @author Quan
 *
 */
public class MobilePaymentActivity extends Activity {
	
	private static final int CREATE_ID = Menu.FIRST;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }
    
    
    /**
     * Create a new menu of create account
     */
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0, CREATE_ID, 0, R.string.main_create_account);
    	return super.onCreateOptionsMenu(menu);
    }
    
    /**
     * When user selects create new account, open Create Account Screen
     */
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case CREATE_ID:
                
                return true;
        }

        return super.onMenuItemSelected(featureId, item);
    }
    
}