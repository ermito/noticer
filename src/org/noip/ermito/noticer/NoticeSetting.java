package org.noip.ermito.noticer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
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
	    
	    if (on) {	    	
	        // Enable
	    	startService(new Intent(this, NoticeService.class));
	    } else {
	        // Disable
	    	stopService(new Intent(this, NoticeService.class));
	    }
	}
	
	
	
	public void onClickStart(View v) {
	     startService(new Intent(this, TestService.class));
	}
	    
	public void onClickStop(View v) {
	     stopService(new Intent(this, TestService.class));	  
	}
	
	

}
