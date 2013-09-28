package org.noip.ermito.noticer;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ToggleButton;
import android.content.Intent;





public class NoticeSetting extends Activity {

	final String LOG_TAG = "myLogs";
	ToggleButton toogleButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice_setting);		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notice_setting, menu);
		return true;
	}
	public void onClickStartStopService(View view) {
	    // Is the toggle on?
	    boolean on = ((ToggleButton) view).isChecked();
	    Intent NSI = new Intent(this, NoticeService.class);
	    CheckBox  checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
	    NSI.putExtra("tray", checkBox1.isChecked());
	    
	    if (on) {	    	
	        // Enable
	    	startService(NSI);
	    } else {
	        // Disable
	    	stopService(NSI);
	    }
	}
	public void onClickChekShowTray(View view) {
	    // Is the toggle on?
		boolean on = ((CheckBox) view).isChecked();
	    boolean servstarted=false;
	    int id=0;
		
		ActivityManager am = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> rs = am.getRunningServices(50);

		for (int i=0; i<rs.size(); i++) 
		{
			ActivityManager.RunningServiceInfo rsi = rs.get(i);
			if(rsi.process.equals("org.noip.ermito.noticer") && rsi.service.getClassName().equals("org.noip.ermito.noticer.NoticeService"))
			{
					Log.d("Service", "Process " + rsi.process + " with component " + rsi.service.getClassName());
					servstarted=true;					
			}
		} 
	    
		if(servstarted)			
			if (on) {				
	        // Enable
				NoticeSender.sendNotif(this);				
				
			} else {
				// Disable
				NoticeSender.nm.cancel(1);  
			}
	}
	
	
	public void onClickStart(View v) {
	     startService(new Intent(this, TestService.class));
	}
	    
	public void onClickStop(View v) {
	     stopService(new Intent(this, TestService.class));	  
	}
	
	

}
