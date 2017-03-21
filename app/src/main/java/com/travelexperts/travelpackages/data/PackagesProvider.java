package com.travelexperts.travelpackages.data;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.travelexperts.travelpackages.data.PackagesContract;

public class PackagesProvider extends ContentProvider {

    // reference to the db helper class that can access the underlying Sqlite db.
    private PackagesDbHelper mPackagesDbHelper;

    // constants for mapping URI's to integers when a call to the content provider is made.
    static final int ALL_PACKAGES_URI_ID =  100; // unique id for Uri's that map to the entire
    // 'packages' table.
    static final int UNIQUE_PACKAGE_URI_ID = 101; // unique id for Uri's that map to a single row
    // in the 'packages' table.

    // variable to access the UriMatcher object to find what Uri was called.
    private static final UriMatcher sUriMatcher = buildUriMatcher();

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
                cursorToReturn = getAllPackages(db);
                break;
            case UNIQUE_PACKAGE_URI_ID:
                cursorToReturn = getPackage(db, passedUri.getPathSegments().get(1));
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + passedUri.toString());
        }

        return cursorToReturn;
    }

    private Cursor getPackage(SQLiteDatabase db, String packageId){
        return db.query(PackagesContract.PackagesEntry.TABLE_NAME, new String[]{PackagesContract
                .PackagesEntry._ID}, null, new String[]{packageId}, null, null, null);
    }

    private Cursor getAllPackages(SQLiteDatabase db) {
        return db.query(PackagesContract.PackagesEntry.TABLE_NAME, null, null, null, null, null,
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
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
