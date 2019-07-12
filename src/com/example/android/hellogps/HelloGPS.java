/*
 * 
 * pm grant com.example.android.hellogps android.permission.ACCESS_FINE_LOCATION
 * 
 * //réglages LocationManagerService.java
 * 
 * settings put global location_background_throttle_package_whitelist com.example.android.hellogps
 * settings put global location_background_throttle_interval_ms 360000
 * 
 * settings list global doit donner:
 * location_background_throttle_interval_ms=360000
 * location_background_throttle_package_whitelist=com.example.android.hellogps
 * 
 * //LocationManagerService.java se pose beaucoup la question de savoir si isImportanceForeground ??
 * am package-importance com.example.android.hellogps
 * 
 * 
 * engine gps en java, garder simple pour utilisation ultérieure.
 */

package com.example.android.hellogps;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.util.Log;



public class HelloGPS extends Activity implements LocationListener {
	
	private static TextView mLatLng;    
	public LocationManager mLocationManager;
	
	 //long: minimum time interval between location updates, in milliseconds
	private static final int MIN_TIME_HIGH = 1000;
	private static final int MIN_TIME_LOW = 10 * 1000; //301 * 1000 -> un peu plus que 5 min car LoctionManagerService.java: max interval a loc request can have and still be considered "high power" HIGH_POWER_INTERVAL_MS = 5 * 60 * 1000;
	
	
    private static final int MIN_DIST = 0; //float: minimum distance between location updates, in meters
    private static final int MIN_DIST_BACKGRND = 0; //au on_stop, on_pause, je veux des updates que quand je bouge
    
    private BaseDeDonnees maBDD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.d("vvnx", "onCreate");
        View view = getLayoutInflater().inflate(R.layout.hello_activity, null);
        setContentView(view);
        
        mLatLng = (TextView) findViewById(R.id.latlng); 
        
        maBDD = new BaseDeDonnees(this);
  
        
        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_HIGH, MIN_DIST, this);
		
		Location lastKnownLocationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		if (lastKnownLocationGPS != null)updateLocText(lastKnownLocationGPS);
          
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("vvnx", "onResume");
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_HIGH, MIN_DIST, this);
    }
    

    
    // utile???
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("vvnx", "onStop");
        //mLocationManager.removeUpdates(this);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_LOW, MIN_DIST_BACKGRND, this);
    }
    

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("vvnx", "onPause");
        //mLocationManager.removeUpdates(this);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_LOW, MIN_DIST_BACKGRND, this);
    }
    
    public static void updateLocText(Location location) {
		Date datefix = new Date(location.getTime());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.FRANCE);
		mLatLng.setText(format.format(datefix) + "\n" + location.getLatitude() + ", " + location.getLongitude() + "\n acc:" + location.getAccuracy() + "\n alt:" + location.getAltitude());
	}    
    
    //implements LocationListener --> il faut les 4 méthodes     
    @Override	
    public void onLocationChanged(Location location) {
		updateLocText(location);
		maBDD.logFix(location.getTime()/1000, location.getLatitude(), location.getLongitude(), location.getAccuracy(), location.getAltitude());
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

