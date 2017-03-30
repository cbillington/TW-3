package com.travelexperts.travelpackages.sync;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.travelexperts.travelpackages.data.PackagesContract;

/**
 * Created by 468364 on 3/29/2017.
 */

public class PackagesContentObserver extends ContentObserver {
    private final Context mContextOfObserver;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public PackagesContentObserver(Handler handler, Context contextOfObserver) {

        super(handler);
        this.mContextOfObserver = contextOfObserver;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        Log.d("hello", "changes observed: ");
        Cursor rows = mContextOfObserver.getContentResolver().query(PackagesContract.PackagesEntry
                        .CONTENT_URI,
                null, null, null, null);

        Log.d("hello", String.valueOf(rows.getCount()));
    }
}
