package org.noip.ermito.noticer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.RawContacts.Data;
import android.telephony.PhoneNumberUtils;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NoticeSender {
	
	static void SendNotice (String mess)
	{
		InetAddress serv_addr = null;
		try {
			serv_addr = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 
    	  
    	  int port= 35876;
          DatagramSocket sock = null;
			try {
				sock = new DatagramSocket();
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	          byte [] buf = mess.getBytes();
	          DatagramPacket pack= new DatagramPacket(buf, buf.length,serv_addr,port);
	          try {
				sock.send(pack);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
          sock.close();
	}
	
	@SuppressLint("NewApi")
	static String NumberToName(Context context,String phoneNumber)
	{
		String Name=" ";
		 phoneNumber = PhoneNumberUtils.stripSeparators(phoneNumber);
         
         String[] projection = new String[]
         		{ ContactsContract.Data.CONTACT_ID,
         		  ContactsContract.Contacts.LOOKUP_KEY,
         		  ContactsContract.Contacts.DISPLAY_NAME,
         		  ContactsContract.Contacts.STARRED,
         		  ContactsContract.Contacts.CONTACT_STATUS,
         		  ContactsContract.Contacts.CONTACT_PRESENCE };

         String selection = "PHONE_NUMBERS_EQUAL(" + 
         					Phone.NUMBER + ",?) AND " + 
         					Data.MIMETYPE + "='" + 
         					Phone.CONTENT_ITEM_TYPE + "'";
         
         String selectionArgs [] ={ phoneNumber };
        /* Cursor cursor;
         if (android.os.Build.VERSION.SDK_INT < 11) {
             cursor = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, projection, selection, selectionArgs, null);
         } else {
             CursorLoader cursorLoader = new CursorLoader(context, ContactsContract.Data.CONTENT_URI, projection, selection, selectionArgs, null);
             cursor = cursorLoader.loadInBackground();
         }*/
         
         
		Cursor cursor = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, projection, selection, selectionArgs, null);
         
         if(cursor.getCount()>0)
         {
         	cursor.moveToFirst();
         	Name = cursor.getString(2) + " ";
         }		
         else Name = "Unknow";
		
		return Name;
	}
	
	
}
