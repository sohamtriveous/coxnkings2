package com.coxnkings.android;

import android.os.Bundle;
import android.view.Menu;

import com.coxnkings.android.R;
import com.coxnkings.android.activity.BaseVoiceActivity;

public class SixthActivity extends BaseVoiceActivity {

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
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	protected void positiveResult(String command) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void negativeResult(String command) {
		// TODO Auto-generated method stub
		
	}
    
}
