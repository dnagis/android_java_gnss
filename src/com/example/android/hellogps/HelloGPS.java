/*
 * 
 * pm grant com.example.android.hellogps android.permission.ACCESS_FINE_LOCATION
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


import android.util.Log;



public class HelloGPS extends Activity  {
	
	private static TextView mLatLng;    
    private LocationVvnx mLocationVvnx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.d("vvnx", "onCreate");
        View view = getLayoutInflater().inflate(R.layout.hello_activity, null);
        setContentView(view);
        
        mLatLng = (TextView) findViewById(R.id.latlng); 
        mLocationVvnx = new LocationVvnx(this);      
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("vvnx", "onResume");
        mLocationVvnx.requestUpdatesFromProvider();
        Location lastLoc = mLocationVvnx.getLastLoc();
        if (lastLoc != null)updateLocText(lastLoc);
    }
    
    public static void updateLocText(Location location) {
		Date datefix = new Date(location.getTime());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.FRANCE);
		mLatLng.setText(format.format(datefix) + "\n" + location.getLatitude() + ", " + location.getLongitude() + "\n acc:" + location.getAccuracy() + "\n alt:" + location.getAltitude());
	}
    
    // utile???
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("vvnx", "onStop");
        //mLocationManager.removeUpdates(this);
    }
    

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("vvnx", "onPause");
    }
    






}

