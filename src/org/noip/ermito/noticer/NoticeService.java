package org.noip.ermito.noticer;

import java.util.concurrent.TimeUnit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;


public class NoticeService extends Service {
	
	final String LOG_TAG = "myLogs";
	
	CallReceiver myCallReceiver;
	MessageReceiver myMessageReceiver;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onCreate() {
	    super.onCreate();
	    Log.d(LOG_TAG, "onCreate");
	        //your code here

        myCallReceiver = new CallReceiver();
        IntentFilter f = new IntentFilter();
        f.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        f.addAction("android.intent.action.PHONE_STATE");
        registerReceiver(myCallReceiver, f);
        
        myMessageReceiver = new MessageReceiver();
        IntentFilter e = new IntentFilter();
        e.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        e.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(myMessageReceiver, e);
        
        NoticeSender.nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
   
	  }
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(intent.getBooleanExtra("tray", true))
			NoticeSender.sendNotif(this);
		return super.onStartCommand(intent, flags, startId);
	  }

	  public void onDestroy() {
		
		unregisterReceiver(myCallReceiver);	
		unregisterReceiver(myMessageReceiver);
		  
		NoticeSender.nm.cancel(1);
	    super.onDestroy();
	    Log.d(LOG_TAG, "onDestroy");
	  }
	  
	
	  

	  
	  
	  
	  


}
