package com.coxnkings.android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;

import com.coxnkings.android.activity.BaseVoiceActivity;
import com.coxnkings.android.application.VoicerecApplication;
import com.coxnkings.android.utils.DateUtils;

public class SecondActivity extends BaseVoiceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_second);
		super.onCreate(savedInstanceState);

		setHintTitle("Select location and date of travel");
		setHintText("From Delhi to Bangalore on November 16");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected boolean processCommand(String command) {
		if (command.contains("from") && command.contains("to")
				&& command.contains("on")) {
			return true;
		}
		return false;
	}

	@Override
	protected void positiveResult(String command) {
		String[] commands = command.split(" ");
		for (int i = 0; i < commands.length; i++) {
			if (commands[i].equals("from")) {
				VoicerecApplication.from = commands[i + 1];
				continue;
			}
			if (commands[i].equals("to")) {
				VoicerecApplication.to = commands[i + 1];
				continue;
			}
			if (commands[i].equals("on")) {
				String month = commands[i + 1];
				String day = commands[i + 2];
				String year = "2013";
				String completeDate = month + " " + day + " " + year;
				VoicerecApplication.date = DateUtils
						.getFormattedDate(completeDate);
				Log.d("Date is ", "date is " + VoicerecApplication.date);
				continue;
			}
		}
		EditText from = (EditText) findViewById(R.id.from);
		EditText to = (EditText) findViewById(R.id.to);
		EditText date = (EditText) findViewById(R.id.date);
		from.setText(VoicerecApplication.from);
		to.setText(VoicerecApplication.to);
		date.setText(VoicerecApplication.date);

		// now that the text has been displayed, wait for a while and then transition
		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(new Intent(SecondActivity.this,
						ThirdActivity.class));
			}
		}, 3000);
	}

	@Override
	protected void negativeResult(String command) {
	}

}
