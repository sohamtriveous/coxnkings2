package com.coxnkings.android.utils;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;

public class VoicerecUtils {
	public static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

	/**
	 * Return an intent to start the speech recognition activity.
	 */
	public static Intent getIntent(String prompt) {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
				"com.coxnkings.android");
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, prompt);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-IN");
		return intent;
	}
	
	public static String processResponse(int requestCode, int resultCode, Intent data) {
		if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
			return data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS).get(0);
		} else {
			return null;
		}
	}
}
