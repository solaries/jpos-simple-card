package com.hqsolution.hqserver.client.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.hqsolution.hqserver.client.listener.OnLoginClickListener;

/**
 * First Activity 
 * 
 * This is the front page of application
 * 
 * @author Quan
 *
 */
public class MobilePaymentActivity extends Activity {
	private static final int ACTIVITY_CREATE=1;
	private static final int CREATE_ID = Menu.FIRST;
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        Button loginButton = (Button)findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new OnLoginClickListener(this));
        
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
            	createAccountScreen();
                return true;
        }

        return super.onMenuItemSelected(featureId, item);
    }
    
    private void createAccountScreen(){
    	Intent i = new Intent(this, CreateAccountActivity.class);
        startActivityForResult(i, ACTIVITY_CREATE);
    }
    
   
    
}