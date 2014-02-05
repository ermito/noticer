package org.noip.ermito.noticer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


public class BootBroadReceiv extends BroadcastReceiver {

	  final String LOG_TAG = "myLogs";

	  public void onReceive(Context context, Intent intent) {
	    Log.d(LOG_TAG, "onReceive " + intent.getAction());
	    
	    SharedPreferences mSettings = context.getSharedPreferences("NoticerSetting", context.MODE_PRIVATE);
		if(mSettings.getBoolean("autoboot", false))
	        context.startService(new Intent(context, NoticeService.class));
	  }
	}
