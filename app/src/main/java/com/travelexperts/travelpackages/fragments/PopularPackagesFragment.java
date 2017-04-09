package com.travelexperts.travelpackages.fragments;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travelexperts.travelpackages.NavigationTestActivity;
import com.travelexperts.travelpackages.R;
import com.travelexperts.travelpackages.adapters.PackagesAdapter;
import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.sync.OnPackagesSortedListener;
import com.travelexperts.travelpackages.sync.PackagesContentObserver;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularPackagesFragment extends Fragment implements OnPackagesSortedListener {


    private Activity mContext;
    private Cursor mPackagesCursor;
    private RecyclerView mRecyclerView;
    private PackagesAdapter mPackagesAdapter;
    private PackagesContentObserver mPackagesObserver;
    TextView mPackageDescTextView;


    public PopularPackagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((NavigationTestActivity)getActivity()).setmCallback(this);
    }

    /**
     * onCreate() for fragments is called BEFORE onCreateView, therefore here is when
     * intitializing any non VIEW related objects is done.
     *
     * @param savedInstanceState
     *
     */
    @TargetApi(25)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                // initialize test data.

                Long randDate2 = ThreadLocalRandom.current().nextLong(123456, 1234567890);
                mContext = getActivity();
<<<<<<< HEAD
                for (int i = 0; i < 10; i++ ){
                    Random rand = new Random();
                    Long n = rand.nextLong();
=======
                Long randPrice2 = ThreadLocalRandom.current().nextLong(500, 4500);

                ContentValues testRow2 = new ContentValues();
                testRow2.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_NAME, "search test");
                testRow2.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_START_DATE, randDate2);
                testRow2.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_END_DATE, "end date");
                testRow2.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_DESCRIPTION, "test " +
                        "description");
                testRow2.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_BASE_PRICE, randPrice2);
                testRow2.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_AGENCY_COMMISSION, 12.12);
                testRow2.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_IMAGE_URL, "http://i" +
                        ".imgur.com/uDvJhu0.jpg");
                mContext.getContentResolver().insert(PackagesContract.PackageEntry
                        .CONTENT_URI_INSERT, testRow2);
                for (int i = 0; i < 10; i++ ){

>>>>>>> c02c7c6ea65d9934f5523b18b97af82b55d9c5aa
                    ContentValues testRow = new ContentValues();
                    testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_NAME, "test name");
                    testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_START_DATE, randDate2);
                    testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_END_DATE, "end date");
                    testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_DESCRIPTION, "test description");
                    testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_BASE_PRICE,
                            randPrice2);
                    testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_AGENCY_COMMISSION, 12.12);
                    testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_IMAGE_URL, "http://i.imgur.com/uDvJhu0.jpg");
                    mContext.getContentResolver().insert(PackagesContract.PackageEntry.CONTENT_URI_INSERT, testRow);
                }

                mPackagesCursor = mContext.getContentResolver().query(PackagesContract
                        .PackageEntry.CONTENT_URI, null, null, null, null);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_popular_packages, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_packages);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mPackagesAdapter = new PackagesAdapter(mContext, mPackagesCursor);
        mPackagesAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mPackagesAdapter);

        mPackagesObserver = new PackagesContentObserver(new Handler(), mPackagesAdapter, mContext);
        mContext.getContentResolver().registerContentObserver(PackagesContract.PackageEntry
                        .CONTENT_URI,
                true,
                mPackagesObserver);

        return rootView;
    }

    @Override
    public void OnPackagesSorted(Cursor newSortedPackages) {
        mPackagesAdapter.swapCursor(newSortedPackages);
    }



}
