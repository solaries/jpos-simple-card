package com.hqsolution.hqserver.client.app.util;


import java.util.List;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.hqsolution.hqserver.client.activity.R;

public class AppUtil {
	public static ListAdapter createListAdapter(Activity activity,
			List<String> items) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
				R.layout.task_row, R.id.rowItem, items);
		return adapter;
	}

	

	
	
	
}
