package com.travelexperts.travelpackages.fragments;

/**
 * Created by 744095 on 3/20/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.travelexperts.travelpackages.R;
import com.travelexperts.travelpackages.adapters.PackagesAdapter;
import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.sync.PackagesContentObserver;

public class popular_tab extends Fragment{

    private PackagesContentObserver mPackagesObserver;
    private PackagesAdapter mPackagesAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = getActivity();
        View rootView = inflater.inflate(R.layout.popular_tab, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_packages);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        Cursor rows = mContext.getContentResolver().query(PackagesContract.PackageEntry
                        .CONTENT_URI,
                null, null, null, null);

        mPackagesAdapter = new PackagesAdapter(mContext, rows);
        mPackagesAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mPackagesAdapter);

        mPackagesObserver = new PackagesContentObserver(new Handler(), mPackagesAdapter, mContext);
        mContext.getContentResolver().registerContentObserver(PackagesContract.PackageEntry
                        .CONTENT_URI,
                true,
                mPackagesObserver);

        ContentValues testRow = new ContentValues();
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_NAME, "test name");
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_START_DATE, "start date");
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_END_DATE, "end date");
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_DESCRIPTION, "test description");
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_BASE_PRICE, 1235.12);
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_AGENCY_COMMISSION, 12.12);

        mContext.getContentResolver().insert(PackagesContract.PackageEntry
                .CONTENT_URI, testRow);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}
