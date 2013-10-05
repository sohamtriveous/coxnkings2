package com.coxnkings.android.activity;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.coxnkings.android.R;
import com.coxnkings.android.utils.VoicerecUtils;

public abstract class BaseVoiceActivity extends Activity {
	// default screen progression delay of 3 s
	private long mDelayInMillis = 3000;

	private String prompt = "Cox & Kings";
	private ImageView mMic = null;
	private TextView mHintText = null;
	private TextView mTitle = null;

	private static Object lock = new Object();

	private static TextToSpeech myTTS = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mMic = (ImageView) findViewById(R.id.voicerec_mic);
		mHintText = (TextView) findViewById(R.id.voicerec_hintText);
		mTitle = (TextView) findViewById(R.id.voicerec_title);

		if (mMic != null) {
			mMic.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					startVoiceRecognition(prompt);
				}
			});
		}

		if (myTTS == null) {
			myTTS = new TextToSpeech(this, new OnInitListener() {
				@Override
				public void onInit(int status) {
					myTTS.setLanguage(Locale.US);
					myTTS.speak(mTitle.getText().toString(), TextToSpeech.QUEUE_ADD, null);
				}
			});
		}
		super.onCreate(savedInstanceState);
	}

	// to start voice recognition with the given prompt
	private void startVoiceRecognition(String prompt) {
		startActivityForResult(VoicerecUtils.getIntent(prompt),
				VoicerecUtils.VOICE_RECOGNITION_REQUEST_CODE);

	}

	// to start voice recognition without any specific prompt
	private void startVoiceRecognition() {
		startActivityForResult(
				VoicerecUtils.getIntent(getResources().getString(
						R.string.app_name)),
				VoicerecUtils.VOICE_RECOGNITION_REQUEST_CODE);
	}

	// to manage the response after a voice rec is done
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		processActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

	// process the responses
	private void processActivityResult(int requestCode, int resultCode,
			Intent data) {
		final String command = VoicerecUtils.processResponse(requestCode,
				resultCode, data);
		// make the hint invisible
		if (mHintText != null) {
			mHintText.setVisibility(View.INVISIBLE);
		}
		// change the title to the processed text
		if (mTitle != null) {
			mTitle.setText(command);
		}
		// find out if the command is valid or not
		boolean result = processCommand(command);
		if (result) {
			Handler h = new Handler();
			h.postDelayed(new Runnable() {
				@Override
				public void run() {
					positiveResult(command);
				}
			}, mDelayInMillis);
		} else {
			mHintText.setVisibility(View.VISIBLE);
			setHintTitle("Incorrect entry, try again");
			negativeResult(command);
		}
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getPrompt() {
		return this.prompt;
	}

	public long getmDelayInMillis() {
		return mDelayInMillis;
	}

	public void setmDelayInMillis(long mDelayInMillis) {
		this.mDelayInMillis = mDelayInMillis;
	}

	public void setHintText(String hint) {
		if (mHintText != null) {
			mHintText.setText(hint);
		}
	}

	public void setHintTitle(String title) {
		if (mTitle != null) {
			mTitle.setText(title);
			speak(title);
		}
	}

	private void speak(String text) {
		Log.d("Base", text);
		if (myTTS != null) {
			myTTS.speak(text, TextToSpeech.QUEUE_ADD, null);
		}
	}

	// needs to be implemented, to process the command
	// returns true if a match is found, false otherwise
	abstract protected boolean processCommand(String command);

	// what to do if a positive result is found
	abstract protected void positiveResult(String command);

	// what to do if a negative result is found
	abstract protected void negativeResult(String command);
}
