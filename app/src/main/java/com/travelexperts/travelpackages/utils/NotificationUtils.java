package com.travelexperts.travelpackages.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.travelexperts.travelpackages.PackageDetailsActivity;
import com.travelexperts.travelpackages.R;
import com.travelexperts.travelpackages.WatchlistedPackagesActivity;

/**
 * Created by 468364 on 4/10/2017.
 */

public class NotificationUtils {

    private static final int WATCHLIST_PACKAGE_MODIFIED_PENDING_INTENT_ID = 599;
    public static final int WATCHLIST_PACKAGE_NOTIFICATION_ID = 598;

    private static PendingIntent contentIntent(Context context, Integer packageId){

        Intent startWatchlistedActivityIntent = new Intent(context, PackageDetailsActivity.class);
        startWatchlistedActivityIntent.putExtra("PackageId", String.valueOf(packageId));

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
                PendingIntent.FLAG_ONE_SHOT);
    }

    @TargetApi(17)
    public static void notifyUserOfWatchlistedPackageChanges(Context context, Integer packageId){

        /**
         *
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
                .setContentIntent(contentIntent(context, packageId))
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH);


        /**
         *
         * Use the android system notification manager to get the system service for displaying
         * our notification
         *
         */
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context
                .NOTIFICATION_SERVICE);

        /**
         *
         *
         * With access to the notification manager system service we can now notify our user
         *
         */

        notificationManager.notify(WATCHLIST_PACKAGE_NOTIFICATION_ID, notificationBuilder.build());
    }
}
