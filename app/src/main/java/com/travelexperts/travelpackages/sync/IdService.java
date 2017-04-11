package com.travelexperts.travelpackages.sync;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by 468364 on 4/10/2017.
 */

public class IdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        try {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Log.d("Firbase id login", "Refreshed token: " + refreshedToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
