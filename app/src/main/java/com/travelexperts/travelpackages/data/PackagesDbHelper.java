package com.travelexperts.travelpackages.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.travelexperts.travelpackages.data.PackagesContract.*;

/**
 * This class represents the lifecycle methods for creating and upgrading a Sqlite Db instance.
 *
 */
public class PackagesDbHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "travelexperts.db";

    private static final int DATABASE_VERSION = 1;


    public PackagesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method creates the database (runs sql to create the tables). This is only run when a
     * connection to the database is attempting to open (such as when getWritableDatabase() is
     * called AND the database does not already exist.
     *
     * @param db: db instance passed when onCreate fires.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createPackagesTableSql = createPackagesTableSql();
        String createBookingsTableSql = createBookingsTableSql();

        db.execSQL(createPackagesTableSql);
        //db.execSQL(createBookingsTableSql);
    }




    /**
     * This method fires when the underlying SQLite DB exists and has a higher version number
     * that was passed to the constructor than the version number stored in the DB file already.
     * This method will destroy the current database and create it from fresh.
     *
     * @param db: SQLite database instance.
     * @param oldVersion: old version number of the database
     * @param newVersion: new version number of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PackageEntry.TABLE_NAME);
        onCreate(db);
    }

    /**
     * This method takes in 0 arguments and returns a completed string to create the database, in this case the database only contains one table.
     *
     * @return: SQL Statement to create the packages table.
     */
    private String createPackagesTableSql() {

        StringBuilder sb = new StringBuilder("CREATE TABLE ").append(PackageEntry.TABLE_NAME)
                .append(" (")
                .append(PackageEntry._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(PackageEntry.COLUMN_PACKAGE_NAME).append(" TEXT, ")
                .append(PackageEntry.COLUMN_PACKAGE_START_DATE).append(" TEXT, ")
                .append(PackageEntry.COLUMN_PACKAGE_END_DATE).append(" TEXT, ")
                .append(PackageEntry.COLUMN_PACKAGE_DESCRIPTION).append(" TEXT, ")
                .append(PackageEntry.COLUMN_PACKAGE_BASE_PRICE).append(" REAL, ")
                .append(PackageEntry.COLUMN_PACKAGE_AGENCY_COMMISSION).append(" REAL); ");

        return sb.toString();

    }

    private String createBookingsTableSql() {
        StringBuilder sb = new StringBuilder("CREATE TABLE ").append(BookingEntry.TABLE_NAME)
                .append(" (")
                .append(BookingEntry._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(BookingEntry.COLUMN_BOOKING_ID).append(" TEXT, ")
                .append(BookingEntry.COLUMN_BOOKING_DATE).append(" TEXT, ")
                .append(BookingEntry.COLUMN_BOOKING_NO).append(" TEXT, ")
                .append(BookingEntry.COLUMN_TRAVELER_COUNT).append(" TEXT, ")
                .append(BookingEntry.COLUMN_CUSTOMER_ID).append(" TEXT, ")
                .append(BookingEntry.COLUMN_TRIP_TYPE_ID).append(" TEXT, ")
                .append(BookingEntry.COLUMN_PACKAGE_ID).append(" TEXT, ")
                .append(BookingEntry.COLUMN_BOOKING_DETAIL).append(" TEXT, ")
                .append(BookingEntry.COLUMN_FEE).append(" TEXT, ")
                .append(BookingEntry.COLUMN_TRIP_TYPE).append(" TEXT); ");


        return sb.toString();
    }
}
