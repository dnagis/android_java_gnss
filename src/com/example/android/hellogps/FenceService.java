package com.example.android.hellogps;


import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import android.util.Log;
import android.media.MediaPlayer;

public class FenceService extends Service {
	

	
	
	@Override
    public void onCreate() {		
		Log.d("vvnx", "onCreate dans FenceService");
        MediaPlayer mp = MediaPlayer.create(this, R.raw.sound);
		mp.start();
    }
    
    // Returns the service's binder object to clients that issue onBind().
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
	
	


}
