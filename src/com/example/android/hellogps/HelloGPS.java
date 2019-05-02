/*
 * 
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

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.util.Log;



public class HelloGPS extends Activity  {
	
	private static TextView mLatLng;    
    private LocationVvnx mLocationVvnx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.hello_activity, null);
        setContentView(view);
        
        mLatLng = (TextView) findViewById(R.id.latlng); 
        mLocationVvnx = new LocationVvnx(this);      
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mLocationVvnx.requestUpdatesFromProvider();
        Location lastLoc = mLocationVvnx.getLastLoc();
        updateLocText(lastLoc);
    }
    
    public static void updateLocText(Location location) {
		mLatLng.setText(location.getLatitude() + ",  " + location.getLongitude() + ",  " + 	location.getAccuracy() + ",  " + 	location.getAltitude() + ",  " + location.getTime());
	}
    
    // utile???
    @Override
    protected void onStop() {
        super.onStop();
        //mLocationManager.removeUpdates(this);
    }
    






}

