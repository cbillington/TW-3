package com.travelexperts.travelpackages;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.travelexperts.travelpackages.adapters.PackagesAdapter;
import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.utils.PreferenceUtils;
import com.travelexperts.travelpackages.utils.WatchlistedPackages;

import java.util.ArrayList;

public class WatchlistedPackagesActivity extends AppCompatActivity {

    private RecyclerView mWatchlistedPackagesRecyclerView;
    private PackagesAdapter mPackagesAdapter;
    private WatchlistedPackages mWatchlistedPackages;
    private ArrayList<Integer> mPackageIds;
    private Cursor mPackagesCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlisted_packages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mWatchlistedPackagesRecyclerView = (RecyclerView)findViewById(R.id.rv_watchlisted_packages);

        mWatchlistedPackages = PreferenceUtils.getWatchlistedPackages(this);

        mPackageIds = mWatchlistedPackages.getPackageIds();

        mPackagesAdapter = new PackagesAdapter(this, null);

        mWatchlistedPackagesRecyclerView.setAdapter(mPackagesAdapter);

        mWatchlistedPackagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // have all package ids --> get packages cursor from content provider
        String selection = "_id IN (" + TextUtils.join(",", mPackageIds) + ")";

        mPackagesCursor = getContentResolver().query(PackagesContract.PackageEntry.CONTENT_URI,
                null, selection, null, null);

        mPackagesAdapter.swapCursor(mPackagesCursor);
    }

}
