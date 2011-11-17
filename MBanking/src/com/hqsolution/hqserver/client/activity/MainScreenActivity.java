package com.hqsolution.hqserver.client.activity;



import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hqsolution.hqserver.client.app.util.AppUtil;

public class MainScreenActivity extends ListActivity {
	private static final int ACTIVITY_ADD_BANK_ACCOUNT = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		createTaskList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	private void createTaskList() {
		List<String> items = new ArrayList<String>();
		items.add("Add Bank Account");
		items.add("Manage Bank Account");
		items.add("Transfer");
		items.add("User Profile");
		items.add("Sign Off");
		ListAdapter adapter = AppUtil.createListAdapter(this, items);
		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		switch (position) {
		case 0:/* Add bank Account */
		{
			Intent i = new Intent(this, AddBankAccountActivity.class);
	        startActivityForResult(i, ACTIVITY_ADD_BANK_ACCOUNT);
			break;
		}
		case 1:
			break;
		case 2:
			break;
		case 3: /* Add bank Account */
		{
			
			break;
		}
		case 4: /* Sign Off */
		{
			this.finish();
			break;
		}
		default:
			break;
		}
	}

}
