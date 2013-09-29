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
	private boolean isRunning=false;
	private static int NOTIFICATION_ID = 8987;
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
        
        
   
	  }
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(!isRunning)
		{
			Notification notif = new Notification(R.drawable.ic_launcher, "Notice service started",	System.currentTimeMillis());  	   
		    // 3-я часть
		    Intent intent1 = new Intent(this, NoticeSetting.class);
		    //intent.putExtra(MainActivity.FILE_NAME, "somefile");
		    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, 0);		   
		    // 2-я часть
		    notif.setLatestEventInfo(this, "Notice service enabled", "For stopped it click me", pIntent);		   
		    // ставим флаг, чтобы уведомление пропало в постоянную секцию
		    notif.flags |= Notification.FLAG_ONGOING_EVENT ;	
			//NoticeSender.sendNotif(this);
			startForeground(NOTIFICATION_ID,  notif );
			isRunning=true;
		}
		return super.onStartCommand(intent, flags, startId);
	  }

	  public void onDestroy() {
	  if (isRunning) {
		  unregisterReceiver(myCallReceiver);	
		  unregisterReceiver(myMessageReceiver);  	      
	      isRunning=false;
	      stopForeground(true);
	    }		
	    super.onDestroy();
	    Log.d(LOG_TAG, "onDestroy");
	  }
	  
	
	  

	  
	  
	  
	  


}
