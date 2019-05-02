package com.example.android.hellogps;


import android.content.Context;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.util.Log;

public class LocationVvnx implements LocationListener {
	
	private Context activityContext;
	public LocationManager mLocationManager;
	    
	private static final int TEN_SECONDS = 10000;
    private static final int TEN_METERS = 10;
	
	public LocationVvnx(Context mContext){
		activityContext = mContext;
		Log.d("vvnx", "constructeur classe location_vvnx");
		setup();
	 }
	 
	 
	
    private void setup() {
		Location gpsLocation = null;
	
		mLocationManager = (LocationManager)activityContext.getSystemService(Context.LOCATION_SERVICE);
		//if (mLocationManager == null) Log.d("vvnx", "lm est null dans setup");			
	
		requestUpdatesFromProvider();        
    }
    
    public void requestUpdatesFromProvider() {
        //Location location = null;
        
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, TEN_SECONDS, TEN_METERS, this);
		//location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        
        //return location;
    }
    
    
    //implements LocationListener     
    @Override	
    public void onLocationChanged(Location location) {
        Log.d("vvnx", location.getLatitude() + ",  " + location.getLongitude());
    }
        
	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
    

}
