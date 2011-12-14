package com.hqsolution.hqserver.client.data.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.hqsolution.hqserver.app.dto.HQAccount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String NAME = "name";
	private static final String DT_ADD = "dt_add";
	// bank_account
	private static final String ACCOUNT_HOLDER = "acc_holder";
	private static final String ACCOUNT_NUMBER = "acc_number";
	private static final String ROUTER_NUMBER = "router";
	private static final String BANK_NAME = "bank_name";

	private static final String TAB_BANK_ACCOUNT = "bank_account";
	private static final String TAB_ACCOUNT_INFO = "account_info";

	public ApplicationDataHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE account_info (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " email TEXT, password TEXT, name TEXT, dt_add DATE);");
		db.execSQL("CREATE TABLE bank_account (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " acc_holder TEXT, acc_number TEXT, router TEXT, bank_name TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// no-op, since will not be called until 2nd schema
		// version exists
	}

	public long insertAccountLogin(HQAccount accountLogin) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		ContentValues cv = new ContentValues();
		cv.put(NAME, accountLogin.getFullName());
		cv.put(PASSWORD, accountLogin.getPassword());
		cv.put(EMAIL, accountLogin.getEmail());
		cv.put(DT_ADD, dateFormat.format(new Date()));
		long newid = getWritableDatabase().insert("account_info", "name", cv);
		return newid;
	}

	/**
	 * login check for a user account
	 * @param username
	 * @param password
	 * @return HQAccount
	 */
	public HQAccount login(String username, String password) {
		/*Cursor cursor = this.getReadableDatabase().rawQuery(
				"SELECT name, email, password FROM " + TAB_ACCOUNT_INFO
						+ " WHERE email = '" + username + "' and password = '"
						+ password + "'", null);*/
		Cursor cursor = this.getReadableDatabase().query(
				TAB_ACCOUNT_INFO,
				new String[] { NAME, EMAIL, PASSWORD },
				EMAIL + " = '" + username + 
				"' AND " + PASSWORD + " = '"
						+ password +"'", null, null, null, null);
		HQAccount acc = null;
		cursor.moveToFirst();
		while (cursor.isAfterLast() == false) {
			acc = new HQAccount(cursor.getString(1), cursor.getString(0),
					cursor.getString(2));
			break;
		}
		return acc;
	}

}
