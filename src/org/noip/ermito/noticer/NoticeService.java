package org.noip.ermito.noticer;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.util.Log;


public class NoticeService extends Service {
	
	final String LOG_TAG = "myLogs";
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onCreate() {
	    super.onCreate();
	    Log.d(LOG_TAG, "onCreate");
	  }
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (startId==1)
		{
			Log.d(LOG_TAG, "onStartCommand");
			ServiceTask();
		}
	    return super.onStartCommand(intent, flags, startId);
	  }

	  public void onDestroy() {
		  if (myThread != null) {
			    Thread dummy = myThread;
				myThread = null;
				dummy.interrupt();
			}
	    super.onDestroy();
	    Log.d(LOG_TAG, "onDestroy");
	  }
	  
	  Thread myThread = new Thread(new Runnable() {
	      public void run() {
	    	 for (int i = 1; i<=50; i++) {
	          Log.d(LOG_TAG, "i = " + i);
	          try {
	            TimeUnit.SECONDS.sleep(1);
	            NoticeSender.SendNotice("i="+i);
	            
	            
	          } catch (InterruptedException e) {
	            e.printStackTrace();
	            stopSelf();
	            return;
	          }
	          
	          if(myThread==null || myThread.isInterrupted()) {stopSelf();  return; }
	        }
	        stopSelf();
	      }
	    });
	  
	  void ServiceTask() {
		  myThread.start();
		
	  }
	  
	  


}
