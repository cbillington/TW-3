package com.travelexperts.travelpackages.sync;

import android.database.Cursor;

/**
 * Created by 468364 on 4/5/2017.
 */

public interface OnPackagesSortedListener {
    void OnPackagesSorted(Cursor newSortedPackages);

}
