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
	private BaseDeDonnees maBDD;

	    
	private static final int MIN_TIME = 1000; //long: minimum time interval between location updates, in milliseconds
    private static final int MIN_DIST = 1; //float: minimum distance between location updates, in meters
	
	//constructeur, récup context de l'activity qui seule peut appeler getSystemService
	public LocationVvnx(Context mContext){
		activityContext = mContext;
		Log.d("vvnx", "constructeur classe location_vvnx");
		maBDD = new BaseDeDonnees(mContext);
		setup();
	}	 
	 
	
    private void setup() {
		Location gpsLocation = null;	
		mLocationManager = (LocationManager)activityContext.getSystemService(Context.LOCATION_SERVICE);
		//if (mLocationManager == null) Log.d("vvnx", "lm est null dans setup");		
		requestUpdatesFromProvider();        
    }
    
    
    public void requestUpdatesFromProvider() {
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, this);
    }
    
    
    public Location getLastLoc() {
		 Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		 return location;
	}
    
    
    //implements LocationListener --> il faut les 4 méthodes     
    @Override	
    public void onLocationChanged(Location location) {
		HelloGPS.updateLocText(location);
		maBDD.logFix(location.getTime(), location.getLatitude(), location.getLongitude(), location.getAccuracy(), location.getAltitude());
        Log.d("vvnx", location.getLatitude() + ",  " + location.getLongitude() + ",  " + 	location.getAccuracy() + ",  " + location.getAltitude() + ",  " + location.getTime());
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
