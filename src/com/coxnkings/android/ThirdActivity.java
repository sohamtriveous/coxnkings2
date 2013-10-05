package com.coxnkings.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.coxnkings.android.R;
import com.coxnkings.android.activity.BaseVoiceActivity;

public class ThirdActivity extends BaseVoiceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_first);
		super.onCreate(savedInstanceState);
		
		setHintTitle("Select the flight");
		setHintText("Example: Select 2");
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

}
