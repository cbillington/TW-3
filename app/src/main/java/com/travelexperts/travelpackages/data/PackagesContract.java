package com.travelexperts.travelpackages.data;


import android.net.Uri;
import android.provider.BaseColumns;

/**
 * This class servers as a database schema for the PackagesProvider.
 *
 */
public class PackagesContract {

    /**
     * These 3 constants provide the base uri of <scheme>://<content-authority> to build all
     * resource endpoints from.
     * <scheme>://<content-authority>/<path>
     *
     */
    public static final String CONTENT_AUTHORITY = "com.travelexperts.travelpackages";
    public static final String SCHEME = "content://";
    public static final Uri BASE_URI = Uri.parse(SCHEME + CONTENT_AUTHORITY);

    // path to packages resource (db table)
    public static final String PATH_PACKAGES = "packages";

    /**
     *
     * inner class to represent the Travel Experts packages table.
     */
    public static final class PackagesEntry implements BaseColumns{
        public static final String TABLE_NAME = "packages";
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_PACKAGES)
                .build();

        public static final String COLUMN_PACKAGE_NAME = "PkgName";
        public static final String COLUMN_PACKAGE_START_DATE = "PkgStartDate";
        public static final String COLUMN_PACKAGE_END_DATE = "PkgEndDate";
        public static final String COLUMN_PACKAGE_DESCRIPTION = "PkgDesc";
        public static final String COLUMN_PACKAGE_BASE_PRICE = "PkgBasePrice";
        public static final String COLUMN_PACKAGE_AGENCY_COMMISSION = "PkgAgencyCommission";
    }
}
