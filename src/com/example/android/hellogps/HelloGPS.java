/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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



public class HelloGPS extends Activity implements LocationListener  {
	
	private TextView mLatLng;
    private Button mFineProviderButton;
    private LocationManager mLocationManager;
    
    private static final int TEN_SECONDS = 10000;
    private static final int TEN_METERS = 10;
    private static final int TWO_MINUTES = 1000 * 60 * 2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for this activity.  You can find it
        // in res/layout/hello_activity.xml
        View view = getLayoutInflater().inflate(R.layout.hello_activity, null);
        setContentView(view);
        
        mLatLng = (TextView) findViewById(R.id.latlng);


        
    }
    
        @Override
    protected void onResume() {
        super.onResume();
        setup();
    }
    
    // Stop receiving location updates whenever the Activity becomes invisible.
    @Override
    protected void onStop() {
        super.onStop();
        mLocationManager.removeUpdates(this);
    }
    

    private void setup() {
        Location gpsLocation = null;
        //mLocationManager.removeUpdates(listener);
        mLatLng.setText(R.string.unknown);
       
		
		
		
		mLocationManager = (LocationManager)getSystemService(this.LOCATION_SERVICE);
        //Log.d("vvnx", "controle positif");
        //if (mLocationManager == null) Log.d("vvnx", "lm est null dans setup");		
		

		gpsLocation = requestUpdatesFromProvider(LocationManager.GPS_PROVIDER, R.string.not_support_gps);
		// Update the UI immediately if a location is obtained.
		if (gpsLocation != null) updateUILocation(gpsLocation);
        
    }
    
    /*private final LocationListener listener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            updateUILocation(location);
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
    }; */  
     
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
            updateUILocation(location);
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
    

    
    private void updateUILocation(Location location) {
        mLatLng.setText(location.getLatitude() + ",  " + location.getLongitude());
    }
}

