package com.travelexperts.travelpackages.sync;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.travelexperts.travelpackages.utils.NotificationUtils;
import com.travelexperts.travelpackages.utils.PreferenceUtils;
import com.travelexperts.travelpackages.utils.WatchlistedPackages;
import com.travelexperts.travelpackages.network.Package;
import java.util.Map;

/**
 * Created by 468364 on 4/10/2017.
 */

public class PackagesMessagingService extends FirebaseMessagingService {

    private final String TAG = PackagesMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {



        Log.d(TAG, "message recieved");

        WatchlistedPackages wp = PreferenceUtils.getWatchlistedPackages(this);

        Map<String, String> data = remoteMessage.getData();
        Log.d("hello", data.get("packageId"));



        Package packageToCheck = new Package();
        packageToCheck.setPackageId(Integer.valueOf(data.get("packageId")));
        if(wp.contains(packageToCheck)){
            NotificationUtils.notifyUserOfWatchlistedPackageChanges(this, packageToCheck.getPackageId());
        }

        NetworkTasks.getPackagesFromNetwork(this);
        super.onMessageReceived(remoteMessage);

    }

    @Override
    public void onDeletedMessages() {
        Log.d("hello", "message deleted");
    }
}
