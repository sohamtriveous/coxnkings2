package com.coxnkings.android;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.coxnkings.android.activity.BaseVoiceActivity;
import com.coxnkings.android.application.FlightInfo;
import com.coxnkings.android.application.VoicerecApplication;
import com.coxnkings.android.utils.DBUtils;
import com.coxnkings.android.utils.DatabaseHelper;

public class ThirdActivity extends BaseVoiceActivity {

	private ListView mListView = null;

	private ArrayList<FlightInfo> mFlights = new ArrayList<FlightInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_third);
		super.onCreate(savedInstanceState);

		setHintTitle("Select the flight");
		setHintText("Example: Select 2");
		
		((TextView) findViewById(R.id.Route)).setText(VoicerecApplication.from + " "+VoicerecApplication.to);
		((TextView) findViewById(R.id.Date)).setText(VoicerecApplication.date);
		
		mListView = (ListView) findViewById(R.id.list);
		mListView.setAdapter(new MyListAdapter(this));

		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean isinit = pref.getBoolean("isInitialised", false);
		DatabaseHelper db = new DatabaseHelper(this);
		if (!isinit) {
			DBUtils.loadDb(this, db);
			Editor edit = pref.edit();
			edit.putBoolean("isInitialised", true);
			edit.commit();
		}
		db.dumpDB();

		mFlights = db.getFlights(VoicerecApplication.from, VoicerecApplication.to, VoicerecApplication.date);
		//mFlights = db.getAllItems();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected boolean processCommand(String command) {
		if (command.contains("select")) {
			return true;
		}
		return false;
	}

	@Override
	protected void positiveResult(String command) {
		String[] commands = command.split(" ");
		// commands[1] will be the row you are looking for
		// example: select 4
		// here commands[1] is the number 4
		// do a cursor traversal for the 4th element from first
		startActivity(new Intent(this, FourthActivity.class));
	}

	@Override
	protected void negativeResult(String command) {
		// TODO Auto-generated method stub

	}

	private class MyListAdapter extends BaseAdapter {
		private LayoutInflater li = null;

		public MyListAdapter(Context context) {
			li = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mFlights.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LinearLayout flightView = null;
			if (null == convertView) {
				flightView = (LinearLayout) li.inflate(R.layout.flight_adapter,
						null);
			} else {
				flightView = (LinearLayout) convertView;
			}

			FlightInfo fi = mFlights.get(position);
			ImageView pic = (ImageView) flightView.findViewById(R.id.pic);
			pic.setImageResource(getImageResId(fi.mAirlineId));

			TextView fname = (TextView) flightView
					.findViewById(R.id.flight_name);
			fname.setText(fi.mFlightName);

			TextView fdata = (TextView) flightView
					.findViewById(R.id.flight_data);
			fdata.setText(fi.mTravelTime + "\n" + fi.mNoOfStops);

			TextView dtime = (TextView) flightView.findViewById(R.id.dept_time);
			dtime.setText("Dep " + fi.mDepartureTime);

			TextView atime = (TextView) flightView
					.findViewById(R.id.arrive_time);
			atime.setText("Arr " + fi.ArriveTime);

			TextView price = (TextView) flightView.findViewById(R.id.price);
			price.setText(fi.mPrice + "/-");

			return flightView;
		}
	}

	private int getImageResId(int id) {
		switch (id) {
		case 1:
			return R.drawable.airindia;
		case 2:
			return R.drawable.goair;
		case 3:
			return R.drawable.spicejet;
		case 4:
			return R.drawable.lufta;
		default:
			return R.drawable.indigo;
		}
	}

}
