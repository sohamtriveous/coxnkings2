package com.coxnkings.android.application;


public class FlightInfo {

	public enum AIRLINES {
		IndiGo, Kingfisher, Jet, AirIndia, Lufthansa, Emirates
	}

	public int mRowId = -1;
	
	public String mFrom = null;
	public String mTo = null;
	public String mDate = null;
	
	
	public int mAirlineId = AIRLINES.IndiGo.ordinal(); // Default
	public String mFlightName = null;
	public String mFlightNumber = null;
	public String mTravelTime = null;
	
	public int mNoOfStops = 0;
	public String mDepartureTime = null;
	public String ArriveTime = null;
	public int mPrice = 0;

}
