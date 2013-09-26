package org.noip.ermito.noticer;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiveServices extends IntentService {

	final String LOG_TAG = "myLogs";
	
	public BroadcastReceiveServices() {
		super("BroadcastReceiveServices");
		// TODO Auto-generated constructor stub
	}
	
	 public void onCreate() {
		    super.onCreate();
	}
	 

	@Override
	protected void onHandleIntent(Intent arg0) {
	    
	    String phoneNumber = arg0.getStringExtra("phoneNumber");
	    String type = arg0.getStringExtra("type");
	    
	    String Name="";
	    Name = NoticeSender.NumberToName(getBaseContext(), phoneNumber);
	    
	    if (type.equals("call"))		
	    	NoticeSender.SendNotice("Вам звонит: "+ Name + phoneNumber );
	    if (type.equals("sms"))
	    {
	    	String mess = arg0.getStringExtra("message");
	    	NoticeSender.SendNotice(Name + phoneNumber + ": " + mess);
	    }
	    
		Log.d(LOG_TAG, "broadcast5");	    		    		
	}
	public void onDestroy() {
		    super.onDestroy();	
	}
}
