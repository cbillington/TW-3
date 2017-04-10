package com.travelexperts.travelpackages.sync;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by 468364 on 4/10/2017.
 */

public class PackagesMessagingService extends FirebaseMessagingService {

    private final String TAG = PackagesMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "message recieved: " + remoteMessage.getFrom());

        super.onMessageReceived(remoteMessage);
    }

    @Override
    public void onDeletedMessages() {
        Log.d("hello", "message deleted");
    }
}
