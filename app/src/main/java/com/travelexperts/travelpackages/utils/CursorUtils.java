package com.travelexperts.travelpackages.utils;

import android.content.Context;
import android.database.Cursor;

import com.travelexperts.travelpackages.data.PackagesContract;

/**
 * Created by 468364 on 4/12/2017.
 */

public class CursorUtils {

    private static String wherePackageIdSelection = "PackageId = ?";

    public static Cursor getPackageById(String packageId, Context context){


        return context.getContentResolver().query(PackagesContract.PackageEntry.CONTENT_URI,
                null,
                wherePackageIdSelection,
                new String[]{packageId},
                null);
    }
}
