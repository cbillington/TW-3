package com.travelexperts.travelpackages.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 468364 on 3/29/2017.
 */

public class PackagesCacheIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *
     */
    public PackagesCacheIntentService() {
        super("PackagesCacheIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        String action = intent.getAction();
        Log.d("hello", action);
        PackageTasks.executeTask(this, action);
    }
}
