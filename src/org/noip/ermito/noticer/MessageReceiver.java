package org.noip.ermito.noticer;
import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;


public class MessageReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		final String LOG_TAG = "myLogs";
		Bundle bundle = intent.getExtras();        
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] msgs = new SmsMessage[pdus.length];
            ArrayList<String> numbers = new ArrayList<String>();
            ArrayList<String> messages = new ArrayList<String>();
            for (int i=0; i<msgs.length; i++){ //пробегаемся по всем полученным сообщениям
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                numbers.add(msgs[i].getOriginatingAddress()); //получаем номер отправителя
                messages.add(msgs[i].getMessageBody().toString());//получаем текст сообщения
            }	
            if (messages.size() > 0){
            	String numb="";
            	for (String c : numbers) {
            		numb+=c;
            	}
            	
            	String mess=": ";
            	for (String c : messages) {
            		mess+=c;
            	}
            	
            	Intent mIntent = new Intent(context, BroadcastReceiveServices.class);
                mIntent.putExtra("phoneNumber", numb);
                mIntent.putExtra("message", mess);
                mIntent.putExtra("type", "sms");            
                context.startService(mIntent);
       	
            	Log.d(LOG_TAG, numb);
            	
            }
        } 

	}

}
