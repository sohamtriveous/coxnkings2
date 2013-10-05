package com.coxnkings.android.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.coxnkings.android.application.FlightInfo;

public class DatabaseHelper extends SQLiteOpenHelper {

	Context mCtx = null;
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "bizdroid";

	private static final String TABLE_FLIGHTS = "flights";

	private static final String KEY_ID = "fid";
	public static final String KEY_FROM = "ffrom";
	public static final String KEY_TO = "fto";
	public static final String KEY_DATE = "fdate";
	public static final String KEY_AIRLINE_ID = "fairline_id";
	public static final String KEY_FLIGHT_NAME = "fname";
	public static final String KEY_FLIGHT_NUMBER = "fnumber";
	public static final String KEY_TRAVEL_TIME = "ftime";
	public static final String KEY_NO_OF_STOPS = "fhops";
	public static final String KEY_DEP_TIME = "fdeptime";
	public static final String KEY_ARR_TIME = "farrtime";
	public static final String KEY_PRICE = "fprice";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mCtx = context;
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_FLIGHTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"

				+ KEY_FROM + " TEXT,"

				+ KEY_TO + " TEXT,"

				+ KEY_DATE + " TEXT,"

				+ KEY_AIRLINE_ID + " INTEGER,"

				+ KEY_FLIGHT_NAME + " TEXT,"

				+ KEY_FLIGHT_NUMBER + " TEXT,"

				+ KEY_TRAVEL_TIME + " TEXT,"

				+ KEY_NO_OF_STOPS + " INTEGER,"

				+ KEY_DEP_TIME + " TEXT,"

				+ KEY_ARR_TIME + " TEXT,"

				+ KEY_PRICE + " INTEGER" + ")";

		db.execSQL(CREATE_CONTACTS_TABLE);	
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLIGHTS);

		// Create tables again
		onCreate(db);
	}

	
	public void addFlightInfo(FlightInfo fi) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FROM, fi.mFrom);
		values.put(KEY_TO, fi.mTo);
		values.put(KEY_DATE, fi.mDate);
		
		values.put(KEY_AIRLINE_ID, fi.mAirlineId);
		values.put(KEY_FLIGHT_NAME, fi.mFlightName);
		values.put(KEY_FLIGHT_NUMBER, fi.mFlightNumber);
		values.put(KEY_TRAVEL_TIME, fi.mTravelTime);
		values.put(KEY_NO_OF_STOPS, fi.mNoOfStops);
		values.put(KEY_DEP_TIME, fi.mDepartureTime);
		values.put(KEY_ARR_TIME, fi.ArriveTime);
		values.put(KEY_PRICE, fi.mPrice);

		// Inserting Row
		db.insert(TABLE_FLIGHTS, null, values);
		db.close(); // Closing database connection
	}

	public ArrayList<FlightInfo> getFlights(String from, String to, String date) {
		ArrayList<FlightInfo> vList = new ArrayList<FlightInfo>();
		// Select All Query
		dumpDB();
		String selectQuery = "SELECT  * FROM " + TABLE_FLIGHTS +" WHERE "+KEY_FROM+" = '"+from+"' AND "+KEY_TO+" = '"+to+"' AND "+KEY_DATE+" = '"+date+"'";
		Log.d("query", selectQuery);
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		//Cursor cursor = db.query(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				FlightInfo fi = new FlightInfo();
				fi.mRowId = Integer.parseInt(cursor.getString(0));
				fi.mFrom = cursor.getString(1);
				fi.mTo = cursor.getString(2);
				fi.mDate = cursor.getString(3);		
						
				fi.mAirlineId = Integer.parseInt(cursor.getString(4));
				fi.mFlightName = cursor.getString(5);
				fi.mFlightNumber = cursor.getString(6);
				fi.mTravelTime = cursor.getString(7);
				
				fi.mNoOfStops = Integer.parseInt(cursor.getString(8));
				fi.mDepartureTime = cursor.getString(9);
				fi.ArriveTime = cursor.getString(10);
				fi.mPrice = Integer.parseInt(cursor.getString(11));
						
				vList.add(fi);
			} while (cursor.moveToNext());
		}
		return vList;
	}


	// Getting All Items
	public ArrayList<FlightInfo> getAllItems() {
		ArrayList<FlightInfo> itmList = new ArrayList<FlightInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_FLIGHTS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				FlightInfo fi = new FlightInfo();
				fi.mRowId = Integer.parseInt(cursor.getString(0));
				fi.mFrom = cursor.getString(1);
				fi.mTo = cursor.getString(2);
				fi.mDate = cursor.getString(3);		
						
				fi.mAirlineId = Integer.parseInt(cursor.getString(4));
				fi.mFlightName = cursor.getString(5);
				fi.mFlightNumber = cursor.getString(6);
				fi.mTravelTime = cursor.getString(7);
				
				fi.mNoOfStops = Integer.parseInt(cursor.getString(8));
				fi.mDepartureTime = cursor.getString(9);
				fi.ArriveTime = cursor.getString(10);
				fi.mPrice = Integer.parseInt(cursor.getString(11));
						
				itmList.add(fi);
			} while (cursor.moveToNext());
		}

		// return contact list
		return itmList;
	}


	// Deleting single contact
	public void deleteAllRows() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FLIGHTS, null, null);
		db.close();
	}

	public void deleteDB() {
		List<FlightInfo> items = getAllItems();

		StringBuilder log3 = new StringBuilder();
		for (FlightInfo fi : items) {
			log3.append(fi.toString() + "\n");
		}
		Log.d("DB", "Emptying DB .. \n" + log3.toString());
		deleteAllRows();
	}

	public void dumpDB() {
		List<FlightInfo> items = getAllItems();

		StringBuilder log1 = new StringBuilder();
		for (FlightInfo fi : items) {
			log1.append(fi.toString() + "\n");
		}
		Log.d("DB", "DB DUMP = \n" + log1.toString());
	}
}
