package com.travelexperts.travelpackages.sync;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by 468364 on 4/5/2017.
 */

public class PackageCursorLoader extends AsyncTaskLoader<Cursor> {

    private Context mContext;

    public PackageCursorLoader(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Cursor loadInBackground() {
        return null;
    }
}
