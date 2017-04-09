package com.travelexperts.travelpackages.utils;

import com.travelexperts.travelpackages.data.PackagesContract;

/**
 * Created by 468364 on 4/8/2017.
 */

public class SearchUtils {

    // selection string
    //  PkgName LIKE ? OR PkgStartDate >= ? OR PkgBasePrice <= ?
    public static String SELECTION = PackagesContract.PackageEntry.COLUMN_PACKAGE_NAME + " LIKE ?" +
            " " +
            "OR " + PackagesContract.PackageEntry.COLUMN_PACKAGE_START_DATE + " >= ? " + "OR " +
            "" + PackagesContract.PackageEntry.COLUMN_PACKAGE_BASE_PRICE + " <= ?";

    public static String[] makeSelectionArgs(String destination, String startDateInMilli, String
            price){

        return new String[] {"%"+destination+"%", startDateInMilli, price};
    }
}
