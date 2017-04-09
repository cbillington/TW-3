package com.travelexperts.travelpackages.data;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class PackagesProvider extends ContentProvider {

    // reference to the db helper class that can access the underlying Sqlite db.
    private PackagesDbHelper mPackagesDbHelper;

    // constants for mapping URI's to integers when a call to the content provider is made.
    static final int ALL_PACKAGES_URI_ID =  100; // unique id for Uri's that map to the entire
    // 'packages' table.
    static final int UNIQUE_PACKAGE_URI_ID = 101; // unique id for Uri's that map to a single row
    // in the 'packages' table.

    static final int INSERT_PACKAGE_URI_ID = 102;

    static final int MODIFY_PACKAGE_URI_ID = 103;

    static final int DELETE_PACKAGE_URI_ID = 104;

    public static final int INSERTED_PACKAGE_URI_ID = 105;

    // variable to access the UriMatcher object to find what Uri was called.
    public static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher(){
        // the UriMatcher is an object that contains the uris to match, once this object is built
        // we can call uriMatcher.match(uri) and it will return an integer if the uri matches
        // what we want, then we can handle that request.
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // matches the request for the entire table of 'packages'.
        uriMatcher.addURI(PackagesContract.CONTENT_AUTHORITY, PackagesContract.PATH_PACKAGES,
                ALL_PACKAGES_URI_ID);

        // matches the request for a single row in 'packages' table.
        // /# is used as a wildcard for any integers on the end of the path.
        uriMatcher.addURI(PackagesContract.CONTENT_AUTHORITY, PackagesContract.PATH_PACKAGES + "/#",
                UNIQUE_PACKAGE_URI_ID);

        // matches request for modifying a single package
        uriMatcher.addURI(PackagesContract.CONTENT_AUTHORITY, PackagesContract.PATH_PACKAGES + "/" +
                PackagesContract.PackageEntry.PATH_MODIFY + "/#", MODIFY_PACKAGE_URI_ID );

        // matches request for inserting package(s)
        uriMatcher.addURI(PackagesContract.CONTENT_AUTHORITY, PackagesContract.PATH_PACKAGES +
                "/" + PackagesContract.PackageEntry.PATH_INSERT, INSERT_PACKAGE_URI_ID);

        // matches request for deleting a single package
        uriMatcher.addURI(PackagesContract.CONTENT_AUTHORITY, PackagesContract.PATH_PACKAGES +
                "/" + PackagesContract.PackageEntry.PATH_DELETE + "/#", DELETE_PACKAGE_URI_ID);

        uriMatcher.addURI(PackagesContract.CONTENT_AUTHORITY, PackagesContract.PATH_PACKAGES +
                "/" + PackagesContract.PackageEntry.PATH_INSERT + "/#", INSERTED_PACKAGE_URI_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {

        mPackagesDbHelper = new PackagesDbHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri passedUri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursorToReturn; // cursor to be returned by quering the underlying db.
        int uriId = sUriMatcher.match(passedUri); // unique id of the uri resource requested.
        SQLiteDatabase db = mPackagesDbHelper.getReadableDatabase(); // db instance.

        switch(uriId){
            case ALL_PACKAGES_URI_ID:

                cursorToReturn = db.query(PackagesContract.PackageEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null,
                        sortOrder);
                break;
            case UNIQUE_PACKAGE_URI_ID:
                cursorToReturn = getPackage(db, passedUri.getPathSegments().get(1));
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + passedUri.toString());
        }

        cursorToReturn.setNotificationUri(getContext().getContentResolver(), passedUri);

        return cursorToReturn;
    }



    private Cursor getPackage(SQLiteDatabase db, String packageId){
        return db.query(PackagesContract.PackageEntry.TABLE_NAME, new String[]{PackagesContract.PackageEntry._ID}, null, new String[]{packageId}, null, null, null);
    }

    private Cursor getAllPackages(SQLiteDatabase db) {
        return db.query(PackagesContract.PackageEntry.TABLE_NAME, null, null, null, null, null,
                null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        // this method checks the Uri and then executes the correct action
        //      The correct action would be to insert the values into the sqllitedb.
        SQLiteDatabase db = mPackagesDbHelper.getWritableDatabase();
        int uriId = sUriMatcher.match(uri);
        Uri uriToReturn = null;

        // make sure uri sent is valid for this endpoint by maintain a set of valid ids
        Set<Integer> validUriIdsForInserting = new HashSet<>();
        validUriIdsForInserting.add(INSERT_PACKAGE_URI_ID);


        if (validUriIdsForInserting.contains(uriId)){

            // insert row into db and get the id of row inserted
            long id = db.insert(PackagesContract.PackageEntry.TABLE_NAME, null, values);

            if(id > 0){

                uriToReturn = ContentUris.withAppendedId(PackagesContract.PackageEntry
                        .CONTENT_URI_INSERT, id);
                Log.d("hello", uriToReturn.toString());
            }

            else{
                throw new android.database.SQLException("Failed to insert row into " + uri);
            }
        }

        else{
            // incorrect uri supplied
            throw new UnsupportedOperationException("Insertion Failed, Incorrect URI Path: " +
            uri);
        }

        getContext().getContentResolver().notifyChange(uriToReturn, null);
        db.close();

        return uriToReturn;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        int numRowsDeleted = 0;
        SQLiteDatabase db = mPackagesDbHelper.getWritableDatabase();

        switch(sUriMatcher.match(uri)){

            case ALL_PACKAGES_URI_ID:
                numRowsDeleted = db.delete(PackagesContract.PackageEntry.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
        Log.d("hello", String.valueOf(numRowsDeleted));
        getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        return numRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mPackagesDbHelper.getWritableDatabase();
        int numRowsInserted = 0;

        switch(sUriMatcher.match(uri)){

            case ALL_PACKAGES_URI_ID:
                db.beginTransaction();


                try{

                    for (ContentValues row: values){
                        long id = db.insert(PackagesContract.PackageEntry.TABLE_NAME, null, row);
                        if (id != -1){
                            numRowsInserted++;
                        }
                    }

                    db.setTransactionSuccessful();
                }
                finally{
                    db.endTransaction();
                }
                break;

            default:
                return super.bulkInsert(uri, values);


        }

        getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        return numRowsInserted;
    }
}
