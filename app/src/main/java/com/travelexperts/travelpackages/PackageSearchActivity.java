package com.travelexperts.travelpackages;

import android.annotation.TargetApi;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FilterQueryProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.travelexperts.travelpackages.adapters.PackagesAdapter;
import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.data.PackagesDbHelper;
import com.travelexperts.travelpackages.fragments.DatePickerDialogFragment;
import com.travelexperts.travelpackages.sync.PackagesContentObserver;
import com.travelexperts.travelpackages.utils.CurrencyTextWatcher;
import com.travelexperts.travelpackages.utils.PackageLoaderUtils;
import com.travelexperts.travelpackages.utils.SearchUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PackageSearchActivity extends AppCompatActivity implements DatePickerDialog
        .OnDateSetListener, LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter mAdapter;
    private AutoCompleteTextView mAutoCompleteTextView;
    private Context mContext;
    private TextInputEditText mStartDateInputEditText;

    private TextInputEditText mPriceInputEditText;
    private DatePickerDialog mStartDatePickerDialog;
    private Button mSearchButton;
    private Calendar mStartDateCalendar;
    private RecyclerView mPackagesSearchRecyclerView;
    private Cursor mPackagesCursor;
    private PackagesAdapter mPackagesAdapter;
    private PackagesContentObserver mPackagesObserver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mStartDateInputEditText = (TextInputEditText)findViewById(R.id.et_start_date);
        mStartDateInputEditText.setText(null);
        mContext = this;
        mAdapter = new SimpleCursorAdapter(this, R.layout.package_name_layout,
                null, new String[]{PackagesContract.PackageEntry.COLUMN_PACKAGE_NAME}, new int[]{R.id
                .tv_package_name_suggestion}, CursorAdapter
                .FLAG_REGISTER_CONTENT_OBSERVER);

        mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.actv_package_name);
        mAutoCompleteTextView.setAdapter(mAdapter);
        mPriceInputEditText = (TextInputEditText)findViewById(R.id.et_price);
        mPriceInputEditText.addTextChangedListener(new CurrencyTextWatcher(mPriceInputEditText,
                "#,###"));
        mAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                Cursor cusorToReturn = getCursor(constraint);
                Log.d("cursor size: ", String.valueOf(cusorToReturn.getCount()));
                return cusorToReturn;
            }
        });

        mAdapter.setCursorToStringConverter(new SimpleCursorAdapter.CursorToStringConverter() {
            @Override
            public CharSequence convertToString(Cursor cursor) {
                int index = cursor.getColumnIndex(PackagesContract.PackageEntry
                        .COLUMN_PACKAGE_NAME);
                return cursor.getString(index);
            }
        });

        mSearchButton = (Button)findViewById(R.id.btn_search);


        mPackagesSearchRecyclerView = (RecyclerView)findViewById(R.id.rv_packages_search);

        mPackagesCursor = mContext.getContentResolver().query(PackagesContract
                .PackageEntry.CONTENT_URI, null, null, null, null);


        mPackagesSearchRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mPackagesAdapter = new PackagesAdapter(mContext, mPackagesCursor);
        mPackagesAdapter.setHasStableIds(true);
        mPackagesSearchRecyclerView.setAdapter(mPackagesAdapter);


        /*mPackagesObserver = new PackagesContentObserver(new Handler(), mPackagesAdapter, this);
        mContext.getContentResolver().registerContentObserver(PackagesContract.PackageEntry
                        .CONTENT_URI,
                true,
                mPackagesObserver);*/

        Log.d("hello", "count in search cursor: " + mPackagesCursor.getCount());
    }

    private Cursor getCursor(CharSequence constraint) {
        Uri packagesBySearchQueryUri = PackagesContract.PackageEntry.CONTENT_URI;
        String whereClause = "PkgName LIKE ?";
        String[] selectionClause = {"%" + constraint + "%"};

        return getContentResolver().query(packagesBySearchQueryUri,
                null,
                whereClause,
                selectionClause,
                null);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }



    public void showDatePickerDialog(View v){


        Calendar now = Calendar.getInstance();
         mStartDatePickerDialog = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        mStartDatePickerDialog.autoDismiss(true);
        mStartDatePickerDialog.setVersion(DatePickerDialog.Version.VERSION_2);
        mStartDatePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }



    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        mStartDateCalendar = calendar;
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        mStartDateInputEditText.setText(format.format(calendar.getTime()));
    }

    /**
     * Search button on click listener
     * @param: button clicked.
     */
    public void searchButtonClicked(View v){
        // the search button has been clicked -- gather information from the fields and make a
        // search query --> execute query --> swap cursor on package adapter to show the selected
        // packages

        // destination query
        String destinationQuery = String.valueOf(mAutoCompleteTextView.getText());
        Log.d("hello", "destination query: " + destinationQuery);
        if(destinationQuery.isEmpty()){
            destinationQuery = "asdfasdfasdfa";
        }

        // start date query
        Long startDateInMilli;
        if (mStartDateCalendar == null){
            startDateInMilli = 123345345643563455L;
        }
        else{
            startDateInMilli = mStartDateCalendar.getTimeInMillis();
        }

        Log.d("hello", "date query: " + String.valueOf(startDateInMilli));

        // price query
        String priceQueryWithCurrencySymbol = String.valueOf(mPriceInputEditText.getText());
        String priceQuery;

        if (priceQueryWithCurrencySymbol.isEmpty()){
            priceQuery = "43523523523";
        }
        else{
            priceQuery = priceQueryWithCurrencySymbol.trim().substring(1);
        }

        Log.d("hello", "price query: " + priceQuery);

        // now we have all the fields --> should send to a cursor loader --> onLoadFinished swap
        // the cursor in the recyclerview adapter.

        Bundle bundleToPassToLoader = PackageLoaderUtils.makeBundleForLoader(destinationQuery,
                String.valueOf
                        (startDateInMilli)
                , priceQuery);

        /*getSupportLoaderManager().restartLoader(PackageLoaderUtils.QUERY_PACKAGES_LOADER_ID,
                bundleToPassToLoader, this);*/
        PackagesDbHelper dbHelper = new PackagesDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from packages where PkgName LIKE ? AND PkgBasePrice <= " +
                "?", new String[]{destinationQuery, priceQuery});

        mPackagesAdapter.swapCursor(c);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == PackageLoaderUtils.QUERY_PACKAGES_LOADER_ID) {

            String selection = SearchUtils.SELECTION;
            String[] selectionArgs = SearchUtils.makeSelectionArgs(args.getString
                    (PackageLoaderUtils.PACKAGE_DESTINATION_KEY), args.getString
                    (PackageLoaderUtils.PACKAGE_START_DATE_KEY), args.getString
                    (PackageLoaderUtils.PACKAGE_PRICE_KEY));

            // testing
            /*String selection = PackagesContract.PackageEntry.COLUMN_PACKAGE_START_DATE + ">= ?";
            String[] selectionArgs = {"123456789089"};*/


            Log.d("hello", "I reached onCreateLoader");
            return new CursorLoader(this,
                    PackagesContract.PackageEntry.CONTENT_URI,
                    null,
                    selection,
                    selectionArgs,
                    null);


        }
        else{
            throw new RuntimeException("Loader not implemented: " + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d("hello", "items in cursor: "+ data.getCount());
        mPackagesAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }



    /*

        Loader Callback methods


     */


}
