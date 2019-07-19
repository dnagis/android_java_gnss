package com.example.android.hellogps;


import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;



import android.util.Log;

public class FenceService extends Service {
	
	
	
	
	@Override
    public void onCreate() {		
		Log.d("vvnx", "onCreate dans FenceService");
    }
    
    // Returns the service's binder object to clients that issue onBind().
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
	
	


}
