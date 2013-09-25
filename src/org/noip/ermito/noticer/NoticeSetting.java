package org.noip.ermito.noticer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Intent;





public class NoticeSetting extends Activity {

	final String LOG_TAG = "myLogs";
	
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
	
	public void onClickStart(View v) {
	     startService(new Intent(this, NoticeService.class));
	}
	    
	public void onClickStop(View v) {
	     stopService(new Intent(this, NoticeService.class));
	   /*  Context context = getBaseContext();
		ComponentName receiver = new ComponentName(context, MessageReceiver.class);

	     PackageManager pm = context.getPackageManager();

	     pm.setComponentEnabledSetting(receiver,
	             PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
	             PackageManager.DONT_KILL_APP);*/
	}
	
	

}
