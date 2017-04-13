package com.travelexperts.travelpackages;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by 468364 on 4/11/2017.
 */

public class TestPackagesMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("message from firebase", remoteMessage.getFrom());
    }
}
