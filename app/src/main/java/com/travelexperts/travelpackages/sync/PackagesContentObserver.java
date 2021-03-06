package com.travelexperts.travelpackages.sync;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.travelexperts.travelpackages.adapters.PackagesAdapter;
import com.travelexperts.travelpackages.data.PackagesContract;

/**
 * Created by 468364 on 3/29/2017.
 */

public class PackagesContentObserver extends ContentObserver {


    private PackagesAdapter mPackagesAdapter;
    private Context mContextOfObserver;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public PackagesContentObserver(Handler handler, PackagesAdapter packagesAdapter, Context
            context) {

        super(handler);
        this.mContextOfObserver = context;
        this.mPackagesAdapter = packagesAdapter;


    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {

        Cursor rows = mContextOfObserver.getContentResolver().query(PackagesContract.PackageEntry
                        .CONTENT_URI,
                null, null, null, null);

        //TODO: implement a switch statement to seperate calls to adapter based on Uri sent.
        //TODO: implement tests for each CRUD statement.


    }


}
