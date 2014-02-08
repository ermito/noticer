package org.noip.ermito.noticer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CallReceiver extends BroadcastReceiver {
	String phoneNumber = "";
	final String LOG_TAG = "myLogs";
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(LOG_TAG, "broadcast");
		// TODO Auto-generated method stub
		if (intent != null && intent.getAction() != null && intent.getAction().equals("android.intent.action.PHONE_STATE")){
        String phone_state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.d(LOG_TAG, "broadcast1");
        if (phone_state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
        	phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        	//Запускаем сервис отсылки номера
        	Intent mIntent = new Intent(context, BroadcastReceiveServices.class);
            mIntent.putExtra("phoneNumber", phoneNumber);
            mIntent.putExtra("type", "call");            
            context.startService(mIntent);   
        } else if (phone_state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            //телефон находится в режиме звонка (набор номера / разговор)
        	} else if (phone_state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
            //телефон находиться в ждущем режиме. Это событие наступает по окончанию разговора, когда мы уже знаем номер и факт звонка
        	}

		}
	}
}
