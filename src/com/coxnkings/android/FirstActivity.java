package com.coxnkings.android;

import com.coxnkings.android.R;
import com.coxnkings.android.activity.BaseVoiceActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;

public class FirstActivity extends BaseVoiceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_first);
		super.onCreate(savedInstanceState);

		setHintTitle("What do you want to do?");
		setHintText("book a flight, book a hotel");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected boolean processCommand(String command) {
		if (command != null) {
			if (command.contains("book") && command.contains("flight")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	protected void positiveResult(String command) {
		startActivity(new Intent(this, SecondActivity.class));
	}

	@Override
	protected void negativeResult(String command) {		
	}

}
