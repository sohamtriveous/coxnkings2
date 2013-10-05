package com.coxnkings.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String getFormattedDate(String normalDate) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM d yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		String resultDate = null;
		try {
			Date date = inputFormat.parse(normalDate);
			resultDate = outputFormat.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultDate;
	}
}
