package com.hqsolution.hqserver.client.app.util;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.hqsolution.hqserver.client.activity.R;

/**
 * Application Util methods
 * 
 * @author QuanLe
 *
 */
public class AppUtil {
	public static ListAdapter createListAdapter(Activity activity,
			List<String> items) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
				R.layout.task_row, R.id.rowItem, items);
		return adapter;
	}

	public static Dialog createExitOnErrorDialog(Activity activity, Exception ex) {
		final Activity act = activity;
		final Exception finalEx = ex;
		final CharSequence[] phoneQuit = { "OK" };
		AlertDialog.Builder builder = new AlertDialog.Builder(activity)
				.setIcon(android.R.attr.alertDialogStyle)
				.setTitle("ERROR" + ex.getMessage())
				.setSingleChoiceItems(phoneQuit, 0,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								act.finish();
							}
						});

		return builder.create();
	}
	
	public static Dialog createMessageDialog(Activity activity, String message) {
		final Activity act = activity;
		final CharSequence[] phoneQuit = { "OK" };
		AlertDialog.Builder builder = new AlertDialog.Builder(activity)
				.setIcon(android.R.attr.alertDialogStyle)
				.setTitle("Pay Attention Please !!!")
				.setMessage(message)
				.setSingleChoiceItems(phoneQuit, 0,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								//do nothing
							}
						});

		return builder.create();
	}
	

}
