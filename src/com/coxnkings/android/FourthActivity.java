package com.coxnkings.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.coxnkings.android.R;
import com.coxnkings.android.activity.BaseVoiceActivity;

public class FourthActivity extends BaseVoiceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_first);
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	protected boolean processCommand(String command) {
		if (command.contains("continue")) {
			return true;
		}
		return false;
	}


	@Override
	protected void positiveResult(String command) {
		// TODO nothing much to do apart from continue on this screen
		startActivity(new Intent(this, FifthActivity.class));
	}


	@Override
	protected void negativeResult(String command) {		
	}
    
}
