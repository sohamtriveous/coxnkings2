package com.coxnkings.android.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.coxnkings.android.application.FlightInfo;

public class DBUtils {

	public static void loadDb(Context ctx, DatabaseHelper db) {
		try {
			AssetManager am = ctx.getAssets();
			InputStream is = am.open("flightdata.txt");
			InputStreamReader inputStreamReader = new InputStreamReader(is);
			BufferedReader f = new BufferedReader(inputStreamReader);
			String line = f.readLine();
			String[] data = null;
			while (line != null) {
				// do stuff
				Log.d("adding", line);
				data = line.split(",");
				FlightInfo fi = new FlightInfo();
				fi.mFrom = data[0];
				fi.mTo = data[1];
				fi.mDate = data[2];

				fi.mAirlineId = Integer.parseInt(data[3]);
				fi.mFlightName = data[4];
				fi.mFlightNumber = data[5];
				fi.mTravelTime = data[6];

				fi.mNoOfStops = Integer.parseInt(data[7]);
				fi.mDepartureTime = data[8];
				fi.ArriveTime = data[9];
				fi.mPrice = Integer.parseInt(data[10]);
				db.addFlightInfo(fi);
				line = f.readLine();				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
