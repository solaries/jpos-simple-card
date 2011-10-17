package home.edu;

import home.edu.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListAdapter;

public class MainScreenActivity extends ListActivity {
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
	
	private void createTaskList(){
		List<String> items = new ArrayList<String>();
		items.add("Add Bank Account");
		items.add("Manage Bank Account");
		items.add("Transfer Between Bank Account");
		ListAdapter adapter = AppUtil.createListAdapter(this, items);
		setListAdapter(adapter);
		
	}
}
