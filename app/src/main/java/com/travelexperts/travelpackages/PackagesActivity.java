package com.travelexperts.travelpackages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelexperts.travelpackages.adapters.PackagesAdapter;
import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.network.CallbackUtils;
import com.travelexperts.travelpackages.network.Package;
import com.travelexperts.travelpackages.network.JacksonConverterFactory;
import com.travelexperts.travelpackages.network.IRestEndpoint;
import com.travelexperts.travelpackages.network.PackagesWrapper;
import com.travelexperts.travelpackages.network.IRestCallback;
import com.travelexperts.travelpackages.network.RestClient;
import com.travelexperts.travelpackages.sync.PackagesContentObserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PackagesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IRestCallback {

    private PackagesContentObserver mPackagesObserver;
    private PackagesAdapter mPackagesAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        //final TextView packagesJsonTextView = (TextView)findViewById(R.id.tv_packages_json);




        /*RestClient restClient = new RestClient();


        // interface for dealing with the response from an asynchronous api call.
        IRestCallback packagesCallback = new IRestCallback() {
            @Override
            public void onSuccess(Response response) {
                *//*
                    If the call was successful, then the response has what you need.
                 *//*
                PackagesWrapper pw = (PackagesWrapper) response.body();
                for(Package pkg:pw.getItems()){
                    packagesJsonTextView.append(pkg.toString() + "\n");
                }

            }
        };

        // Pass the call and callback instance to a utility.
        CallbackUtils.requestPackagesFromCall(restClient.getApiService().getPackages(), packagesCallback);*/


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_packages);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor rows = this.getContentResolver().query(PackagesContract.PackageEntry
                        .CONTENT_URI,
                null, null, null, null);

        mPackagesAdapter = new PackagesAdapter(this, rows);
        mPackagesAdapter.setHasStableIds(true);
        mRecyclerView.setAdapter(mPackagesAdapter);


        mContext.getContentResolver().registerContentObserver(PackagesContract.PackageEntry
                        .CONTENT_URI,
                true,
                mPackagesObserver);



        /*ContentValues testRow = new ContentValues();
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_NAME, "test name");
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_START_DATE, "start date");
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_END_DATE, "end date");
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_DESCRIPTION, "test description");
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_BASE_PRICE, 1235.12);
        testRow.put(PackagesContract.PackageEntry.COLUMN_PACKAGE_AGENCY_COMMISSION, 12.12);

        mContext.getContentResolver().insert(PackagesContract.PackageEntry
                .CONTENT_URI, testRow);*/










    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.packages, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSuccess(Response response) {

    }
}
