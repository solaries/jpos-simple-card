package home.edu.app.util;

import home.edu.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class AppUtil {
	public static ListAdapter createListAdapter(Activity activity,
			List<String> items) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
				R.layout.task_row, R.id.rowItem, items);
		return adapter;
	}

	

	
	
	
}
