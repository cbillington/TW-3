package com.travelexperts.travelpackages.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.travelexperts.travelpackages.data.PackagesContract.*;
/**
 * Created by 468364 on 3/20/2017.
 */

public class PackagesDbHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "travelexperts.db";

    private static final int DATABASE_VERSION = 1;

    public PackagesDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = createTableSql();
        db.execSQL(createTableSql);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PackagesEntry.TABLE_NAME);
        onCreate(db);
    }


    private String createTableSql() {

        StringBuilder sb = new StringBuilder("CREATE TABLE ").append(PackagesEntry.TABLE_NAME)
                .append(" (")
                .append(PackagesEntry._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append(PackagesEntry.COLUMN_PACKAGE_NAME).append(" TEXT, ")
                .append(PackagesEntry.COLUMN_PACKAGE_START_DATE).append(" TEXT, ")
                .append(PackagesEntry.COLUMN_PACKAGE_END_DATE).append("TEXT, ")
                .append(PackagesEntry.COLUMN_PACKAGE_DESCRIPTION).append(" TEXT, ")
                .append(PackagesEntry.COLUMN_PACKAGE_BASE_PRICE).append(" REAL, ")
                .append(PackagesEntry.COLUMN_PACKAGE_AGENCY_COMMISSION).append("); ");

        return sb.toString();

    }
}
