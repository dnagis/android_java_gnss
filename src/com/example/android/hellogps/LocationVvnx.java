package com.example.android.hellogps;


import android.content.Context;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.util.Log;

public class LocationVvnx implements LocationListener {
	
	private Context activityContext;
	private LocationManager mLocationManager;
	    
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
		//Log.d("vvnx", "controle positif");
		//if (mLocationManager == null) Log.d("vvnx", "lm est null dans setup");		
		
	
		gpsLocation = requestUpdatesFromProvider(LocationManager.GPS_PROVIDER, R.string.not_support_gps);
		// Update the UI immediately if a location is obtained.
		//if (gpsLocation != null) logUILocation(gpsLocation);
        
    }
    
    private Location requestUpdatesFromProvider(final String provider, final int errorResId) {
        Location location = null;
        
		//if (mLocationManager == null) Log.d("vvnx", "lm est null dans requestUpdatesFromProvider");	
        //if (provider == null) Log.d("vvnx", "provider est null");
        
		mLocationManager.requestLocationUpdates(provider, TEN_SECONDS, TEN_METERS, this);
		location = mLocationManager.getLastKnownLocation(provider);
        
        return location;
    }
    
    
    	//implements LocationListener     
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
