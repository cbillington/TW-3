package com.travelexperts.travelpackages.data;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;


@RunWith(AndroidJUnit4.class)
public class PackagesProviderTest {

    private final Context mContext = InstrumentationRegistry.getTargetContext();

    @Before
    public void setUp() throws Exception {
        PackagesDbHelper dbHelper = new PackagesDbHelper(mContext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(PackagesContract.PackageEntry.TABLE_NAME, null, null);
    }

    private static final Uri TEST_PACKAGES = PackagesContract.PackageEntry.CONTENT_URI;
    // Content URI for a single task with id = 1
    private static final Uri TEST_PACKAGES_WITH_ID = TEST_PACKAGES.buildUpon().appendPath("1").build();

    private static final Uri TEST_INSERT_PACKAGE_URI_ID = PackagesContract.PackageEntry.CONTENT_URI_INSERT;


    private static final Uri TEST_INSERTED_PACKAGE_URI_ID = PackagesContract.PackageEntry.CONTENT_URI_INSERT.buildUpon().appendPath("1").build();

    @Test
    public void buildUriMatcher() throws Exception {
        /* Create a URI matcher that the PackagesProvider uses */
        UriMatcher testMatcher = PackagesProvider.buildUriMatcher();

        /* Test that the code returned from our matcher matches the expected TASKS int */
        String tasksUriDoesNotMatch = "Error: The TASKS URI was matched incorrectly.";
        int actualTasksMatchCode = testMatcher.match(TEST_PACKAGES);
        int expectedTasksMatchCode = PackagesProvider.ALL_PACKAGES_URI_ID;
        assertEquals(tasksUriDoesNotMatch,
                actualTasksMatchCode,
                expectedTasksMatchCode);

        /* Test that the code returned from our matcher matches the expected TASK_WITH_ID */
        String taskWithIdDoesNotMatch =
                "Error: The TASK_WITH_ID URI was matched incorrectly.";
        int actualTaskWithIdCode = testMatcher.match(TEST_PACKAGES_WITH_ID);
        int expectedTaskWithIdCode = PackagesProvider.UNIQUE_PACKAGE_URI_ID;
        assertEquals(taskWithIdDoesNotMatch,
                actualTaskWithIdCode,
                expectedTaskWithIdCode);

        // test that inserting a new package uri id matches
        String packageInsertUriDoesNotMatch = "Error: the INSERT_PACKAGE_URI_ID was matched incorrectly";
        int actualIdInsertPackageIdCode = testMatcher.match(TEST_INSERT_PACKAGE_URI_ID);
        int expectedInsertPackageIdCode = PackagesProvider.INSERT_PACKAGE_URI_ID;
        assertEquals(packageInsertUriDoesNotMatch, actualIdInsertPackageIdCode, expectedInsertPackageIdCode);

        // test that an INSERTED package uri id matches -- used for notifiying observers of
        // exactly what position changed.
        String packageInsertedUriDoesNotMatch = "Error: the INSERTED_PACKAGE_URI_ID was matched incorrectly";
        int actualIdOfInsertedCode = testMatcher.match(TEST_INSERTED_PACKAGE_URI_ID);
        int expectedInsertedPakcageUriId = PackagesProvider.INSERTED_PACKAGE_URI_ID;
        assertEquals(packageInsertedUriDoesNotMatch, actualIdOfInsertedCode, expectedInsertedPakcageUriId);




    }

    @Test
    public void testProviderRegistry() {

        /*
         * A ComponentName is an identifier for a specific application component, such as an
         * Activity, ContentProvider, BroadcastReceiver, or a Service.
         *
         * Two pieces of information are required to identify a component: the package (a String)
         * it exists in, and the class (a String) name inside of that package.
         *
         * We will use the ComponentName for our ContentProvider class to ask the system
         * information about the ContentProvider, specifically, the authority under which it is
         * registered.
         */
        String packageName = mContext.getPackageName();
        String taskProviderClassName = PackagesProvider.class.getName();
        ComponentName componentName = new ComponentName(packageName, taskProviderClassName);

        try {

            /*
             * Get a reference to the package manager. The package manager allows us to access
             * information about packages installed on a particular device. In this case, we're
             * going to use it to get some information about our ContentProvider under test.
             */
            PackageManager pm = mContext.getPackageManager();

            /* The ProviderInfo will contain the authority, which is what we want to test */
            ProviderInfo providerInfo = pm.getProviderInfo(componentName, 0);
            String actualAuthority = providerInfo.authority;
            String expectedAuthority = packageName;

            /* Make sure that the registered authority matches the authority from the Contract */
            String incorrectAuthority =
                    "Error: PackagesProvider registered with authority: " + actualAuthority +
                            " instead of expected authority: " + expectedAuthority;
            assertEquals(incorrectAuthority,
                    actualAuthority,
                    expectedAuthority);

        } catch (PackageManager.NameNotFoundException e) {
            String providerNotRegisteredAtAll =
                    "Error: PackagesProvider not registered at " + mContext.getPackageName();
            /*
             * This exception is thrown if the ContentProvider hasn't been registered with the
             * manifest at all. If this is the case, you need to double check your
             * AndroidManifest file
             */
            fail(providerNotRegisteredAtAll);
        }
    }

    @Test
    public void testQuery() throws Exception {
        /* Get access to a writable database */
        PackagesDbHelper dbHelper = new PackagesDbHelper(mContext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        /* Create values to insert */
        ContentValues testTaskValues = new ContentValues();
        testTaskValues.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_NAME, "test name");
        testTaskValues.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_START_DATE, "start date");
        testTaskValues.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_END_DATE, "end date");
        testTaskValues.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_DESCRIPTION, "test description");
        testTaskValues.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_BASE_PRICE, 1235.12);
        testTaskValues.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_AGENCY_COMMISSION, 12.12);

        /* Insert ContentValues into database and get a row ID back */
        long taskRowId = database.insert(
                /* Table to insert values into */
                PackagesContract.PackageEntry.TABLE_NAME,
                null,
                /* Values to insert into table */
                testTaskValues);

        String insertFailed = "Unable to insert directly into the database";
        assertTrue(insertFailed, taskRowId != -1);

        /* We are done with the database, close it now. */
        database.close();

        /* Perform the ContentProvider query */
        Cursor taskCursor = mContext.getContentResolver().query(
                PackagesContract.PackageEntry.CONTENT_URI,
                /* Columns; leaving this null returns every column in the table */
                null,
                /* Optional specification for columns in the "where" clause above */
                null,
                /* Values for "where" clause */
                null,
                /* Sort order to return in Cursor */
                null);


        String queryFailed = "Query failed to return a valid Cursor";
        assertTrue(queryFailed, taskCursor != null);

        /* We are done with the cursor, close it now. */
        taskCursor.close();
    }

    @Test
    public void testBulkInsert() {
        // bulk insert takes an array of content values and inserts them into the database as a
        // transaction.
        int NUM_ROWS_TO_TEST = 4;
        // create test data.
        // initialize an array of content values
        ContentValues[] testDataContentValues = new ContentValues[NUM_ROWS_TO_TEST];

        for (int i = 0; i < NUM_ROWS_TO_TEST; i++ ){
            ContentValues testRow = new ContentValues();
            testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_NAME, "test name");
            testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_START_DATE, "start date");
            testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_END_DATE, "end date");
            testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_DESCRIPTION, "test description");
            testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_BASE_PRICE, 1235.12);
            testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_AGENCY_COMMISSION, 12.12);
            testDataContentValues[i] = testRow;
        }


        // open connection to db
        PackagesDbHelper dbHelper = new PackagesDbHelper(mContext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // execute bulk insert
        int numRowsInserted = mContext.getContentResolver().bulkInsert(PackagesContract.PackageEntry.CONTENT_URI, testDataContentValues);

        // close connection
        database.close();

        String insertFailed = "Bulk insert failed to insert test rows";
        String numRowsWrong = "Bulk insert failed to insert the correct number of rows";

        // assert that the number of rows inserted is not 0 and that the number of rows inserted
        // is not different than what was expected.
        assertTrue(insertFailed, numRowsInserted != 0);
        assertTrue(numRowsWrong, numRowsInserted == NUM_ROWS_TO_TEST);
    }


    @Test
    public void testInsert(){
        PackagesDbHelper dbHelper = new PackagesDbHelper(mContext);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // we need to make sure the returned URI follows the pattern of
        // content://<authority>/packages/insert/#
        int RETURN_URI_ID = 1;

        // create a uri matcher for the pattern
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PackagesContract.CONTENT_AUTHORITY, PackagesContract.PATH_PACKAGES + "/" + PackagesContract.PackageEntry.PATH_INSERT + "/#", 1);

        ContentValues testRow = new ContentValues();
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_NAME, "test name");
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_START_DATE, "start date");
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_END_DATE, "end date");
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_DESCRIPTION, "test description");
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_BASE_PRICE, 1235.12);
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_AGENCY_COMMISSION, 12.12);

        Uri insertedRowUri = mContext.getContentResolver().insert(PackagesContract.PackageEntry
                        .CONTENT_URI_INSERT,
                testRow);

        database.close();

        // make sure returned uri contains the proper structure
        // content://<authority>/packages/insert/#
        String uriDoesntMatch = "Uri does not match convention for insert: " + insertedRowUri;
        assertTrue(uriDoesntMatch, uriMatcher.match(insertedRowUri) == RETURN_URI_ID);



    }

    //TODO: test new implementations of provider endpoints (modify, delete)

}