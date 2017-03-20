package com.travelexperts.travelpackages.data;


import android.provider.BaseColumns;

/**
 * This class servers as a database schema for the PackagesProvider.
 *
 */
public class PackagesContract {

    /**
     *
     * inner class to represent the Travel Experts packages table.
     */
    public static final class PackagesEntry implements BaseColumns{
        public static final String TABLE_NAME = "packages";

        public static final String COLUMN_PACKAGE_NAME = "PkgName";
        public static final String COLUMN_PACKAGE_START_DATE = "PkgStartDate";
        public static final String COLUMN_PACKAGE_END_DATE = "PkgEndDate";
        public static final String COLUMN_PACKAGE_DESCRIPTION = "PkgDesc";
        public static final String COLUMN_PACKAGE_BASE_PRICE = "PkgBasePrice";
        public static final String COLUMN_PACKAGE_AGENCY_COMMISSION = "PkgAgencyCommission";
    }
}
