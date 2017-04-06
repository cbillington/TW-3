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
    public static final String PATH_BOOKINGS = "bookings";



    /**
     *
     * inner class to represent the Travel Experts packages table.
     */
    public static final class PackageEntry implements BaseColumns{

        //endpoints
        public static final String PATH_MODIFY = "modify";
        public static final String PATH_DELETE = "delete";
        public static final String PATH_INSERT = "insert";


        // TODO: implement changes to provider
        // endpoint uri's
        public static final Uri CONTENT_URI_MODIFY = BASE_URI.buildUpon().appendPath
                (PATH_PACKAGES).appendPath
                (PATH_MODIFY).build();
        public static final Uri CONTENT_URI_DELETE = BASE_URI.buildUpon().appendPath
                (PATH_PACKAGES).appendPath
                (PATH_DELETE)
                .build();
        public static final Uri CONTENT_URI_INSERT = BASE_URI.buildUpon().appendPath
                (PATH_PACKAGES).appendPath
                (PATH_INSERT).build();

        // table name
        public static final String TABLE_NAME = "packages";

        // base uri
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_PACKAGES)
                .build();

        public static final int NUM_COLUMNS = 6;

        // column names
        public static final String COLUMN_PACKAGE_ID = "PackageId";
        public static final String COLUMN_PACKAGE_NAME = "PkgName";
        public static final String COLUMN_PACKAGE_START_DATE = "PkgStartDate";
        public static final String COLUMN_PACKAGE_END_DATE = "PkgEndDate";
        public static final String COLUMN_PACKAGE_DESCRIPTION = "PkgDesc";
        public static final String COLUMN_PACKAGE_BASE_PRICE = "PkgBasePrice";
        public static final String COLUMN_PACKAGE_AGENCY_COMMISSION = "PkgAgencyCommission";
        public static final String COLUMN_PACKAGE_IMAGE_URL = "PkgImgUrl";

    }

    public static final class BookingEntry implements BaseColumns {



        // table name
        public static final String TABLE_NAME = "bookings";

        // base
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_BOOKINGS)
                .build();
        public static final int NUM_COLUMNS = 10;


        // paths





        public static final String COLUMN_BOOKING_ID = "BookingId";
        public static final String COLUMN_BOOKING_DATE = "BookingId";
        public static final String COLUMN_BOOKING_NO = "BookingNo";
        public static final String COLUMN_TRAVELER_COUNT = "TravelCount";
        public static final String COLUMN_CUSTOMER_ID = "CustomerId";
        public static final String COLUMN_TRIP_TYPE_ID = "TripTypeId";
        public static final String COLUMN_PACKAGE_ID = "PackageId";
        public static final String COLUMN_BOOKING_DETAIL = "BookingDetail";
        public static final String COLUMN_FEE = "Fee";
        public static final String COLUMN_TRIP_TYPE = "TripType";

    }
}
