package com.travelexperts.travelpackages;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.travelexperts.travelpackages.adapters.PackagesAdapter;
import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.utils.PreferenceUtils;
import com.travelexperts.travelpackages.utils.WatchlistedPackages;
import com.travelexperts.travelpackages.network.Package;
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

        getWatchlistedPackagesCursor();

        mPackagesAdapter = new PackagesAdapter(this, null);
        mPackagesAdapter.setHasStableIds(true);
        mWatchlistedPackagesRecyclerView.setAdapter(mPackagesAdapter);

        mWatchlistedPackagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        mPackagesAdapter.swapCursor(mPackagesCursor);


        ItemTouchHelper.SimpleCallback itemSwipeCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder packageView, int direction) {
                Log.d("WatchlistedPackages", "swiped direction: " + direction);

                // here we need to handle when they swipe

                // when they swipe --> capture the packageid and remove it from the watchlisted
                // packages


                // when swiped, capture Id
                Long packageId = packageView.getItemId(); // this has the package id underneathe

                // we have the packageId now --> we can remove from watchlistedpackages preference

                // get the watchlisted packages


                // make new package to remove
                Package packageToRemove = new Package();
                packageToRemove.setPackageId((int)(long)packageId);

                // remove
                PreferenceUtils.removePackageFromWatchlist(packageToRemove, getApplicationContext());

                // get new watchlisted packages cursor and notify recyclver of change.
                getWatchlistedPackagesCursor();
                mPackagesAdapter.swapCursor(mPackagesCursor);
                mPackagesAdapter.notifyDataSetChanged();

                Snackbar snack = Snackbar.make(findViewById(R.id.watchlisted_packages_layout), "Package removed from " +
                                "watchlist",
                        Snackbar
                        .LENGTH_SHORT);
                snack.show();
            }
        };

        ItemTouchHelper itemSwipeHelper = new ItemTouchHelper(itemSwipeCallback);

        itemSwipeHelper.attachToRecyclerView(mWatchlistedPackagesRecyclerView);
    }

    private void getWatchlistedPackagesCursor() {
        mWatchlistedPackages = PreferenceUtils.getWatchlistedPackages(this);

        mPackageIds = mWatchlistedPackages.getPackageIds();

        String selection = "PackageId IN (" + TextUtils.join(",", mPackageIds) + ")";

        mPackagesCursor = getContentResolver().query(PackagesContract.PackageEntry.CONTENT_URI,
                null, selection, null, null);
    }

}
