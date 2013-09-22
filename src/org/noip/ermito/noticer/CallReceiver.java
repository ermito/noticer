package org.noip.ermito.noticer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.RawContacts.Data;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;

public class CallReceiver extends BroadcastReceiver {
	String phoneNumber = "";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals("android.intent.action.PHONE_STATE")){
        String phone_state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (phone_state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            //телефон звонит, получаем входящий номер
            phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            String Name = NoticeSender.NumberToName(context, phoneNumber);
                   
            
            NoticeSender.SendNotice("Вам звонит: "+ Name + phoneNumber );
            
        } else if (phone_state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            //телефон находится в режиме звонка (набор номера / разговор)
        	} else if (phone_state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
            //телефон находиться в ждущем режиме. Это событие наступает по окончанию разговора, когда мы уже знаем номер и факт звонка
        	}

		}
	}
}
