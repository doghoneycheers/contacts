package com.example.q.contacts;

import android.app.Activity;
import android.database.Cursor;
import android.provider.ContactsContract;
//import android.content.Context;
import android.util.Log;

public class Main_Activity extends Activity {

    public void GetUserContactsList(){
        String[] arrProjection={
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };
        String[] arrPhoneProjection={
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        Cursor clsCursor=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, arrProjection,
                ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1",
                null, null);

        while(clsCursor.moveToNext()){
            String strContactId=clsCursor.getString(0);

            Log.d("Unity", "ID: " + clsCursor.getString(0));
            Log.d("Unity", "Name: " + clsCursor.getString(1));

            Cursor clsPhoneCursor=getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    arrPhoneProjection,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + strContactId,
                    null, null);

            while(clsPhoneCursor.moveToNext()){
                Log.d("Unity", "Phone Number: " + clsPhoneCursor.getString(0));
            }
            clsPhoneCursor.close();
        }
    }
}
