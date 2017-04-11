package com.travelexperts.travelpackages.sync;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.travelexperts.travelpackages.utils.PreferenceUtils;
import com.travelexperts.travelpackages.utils.WatchlistedPackages;

/**
 * Created by 468364 on 4/10/2017.
 */

public class PackagesMessagingService extends FirebaseMessagingService {

    private final String TAG = PackagesMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {



        Log.d(TAG, "message recieved: " + remoteMessage.getFrom());

        WatchlistedPackages wp = PreferenceUtils.getWatchlistedPackages(this);

        Log.d("hello", String.valueOf(wp.getItems().size()));



    }

    @Override
    public void onDeletedMessages() {
        Log.d("hello", "message deleted");
    }
}
