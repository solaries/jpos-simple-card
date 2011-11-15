package com.hqsolution.hqserver.client.data.helper;

import com.hqsolution.hqserver.app.dto.AccountLogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @author Quan
 * 
 */
public class ApplicationDataHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "banking.db";
	private static final int SCHEMA_VERSION = 1;

	public ApplicationDataHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE account_info (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " email TEXT, password TEXT, name TEXT, dt_add DATE);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// no-op, since will not be called until 2nd schema
		// version exists
	}

	public void insert(AccountLogin accountLogin) {
		ContentValues cv = new ContentValues();
		cv.put("name", accountLogin.getFullname());
		cv.put("password", accountLogin.getPassword());
		cv.put("email", accountLogin.getEmail());
		long newid = getWritableDatabase().insert("account_info", "name", cv);
	}

}
