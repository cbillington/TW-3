package com.travelexperts.travelpackages.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.travelexperts.travelpackages.R;

/**
 * Created by 468364 on 4/10/2017.
 */

public class NotificationUtils {

    private static final int WATCHLIST_PACKAGE_MODIFIED_PENDING_INTENT_ID = 599;

    private static PendingIntent contentIntent(Context context){

        Intent startWatchlistedActivityIntent = new Intent(context, WatchlistedPackages.class);

        /**
         *
         * This will return a pending intent to start when the pushed message goes through AND the
         * user has that package watchlisted.
         *
         */

        return PendingIntent.getActivity(
                context,
                WATCHLIST_PACKAGE_MODIFIED_PENDING_INTENT_ID,
                startWatchlistedActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void notifyUserOfWatchlistedPackageChanges(Context context){

        /**
         * First create the notification using Notification builder
         *
         */
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_menu_manage)
                .setContentTitle("Travel Experts")
                .setContentText("One or more of your watchlisted packages has been modifed")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Hello"))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);

    }
}
