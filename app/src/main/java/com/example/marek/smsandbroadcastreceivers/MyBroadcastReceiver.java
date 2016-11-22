package com.example.marek.smsandbroadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public MyBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //the intent will contain the contents and phone number of the received message(s)
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        //below is the notorious for-each loop in java "for each message in messages"
        for(SmsMessage message : messages){
            System.out.println("SMS INTERCEPTED! " + message.getDisplayMessageBody() + " FROM " + message.getDisplayOriginatingAddress());
            //what are some evil things we could do here?
            //save the message to a database?
            //try to modify the message & rebroadcast.. etc.
        }

        //also attempt to send a message - testing this with multiple emulators will be left as an exercise!
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("15555551234", null, "Hey bro, download this awesome SMS app mwa ha ha", null, null);
    }
}
