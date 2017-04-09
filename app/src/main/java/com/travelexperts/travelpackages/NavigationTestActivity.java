package com.travelexperts.travelpackages;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.travelexperts.travelpackages.adapters.PackageTabPagerAdapter;
import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.data.PackagesDbHelper;
import com.travelexperts.travelpackages.sync.OnPackagesSortedListener;

public class NavigationTestActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoaderManager
        .LoaderCallbacks<Cursor> {

    private OnPackagesSortedListener mCallback;

    private ViewPager mPackageTabViewPager;
    public static final String SORT_BY_PRICE = "sort-by-price";

    private static final int ID_PACKAGES_BY_PRICE_LOADER = 100;
    private static final int ID_PACKAGES_BY_DATE_LOADER = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SQLiteDatabase db = new PackagesDbHelper(this).getWritableDatabase();

        //db.execSQL("DROP TABLE IF EXISTS " + PackagesContract.PackageEntry.TABLE_NAME);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mPackageTabViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPackageTabViewPager.setAdapter(new PackageTabPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mPackageTabViewPager);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.navigation_test, menu);
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

        if (id == R.id.action_sort_price){
            getSupportLoaderManager().initLoader(ID_PACKAGES_BY_PRICE_LOADER, null, this);
        }

        if (id == R.id.action_sort_date){
            getSupportLoaderManager().initLoader(ID_PACKAGES_BY_DATE_LOADER, null, this);
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
            Intent openAccountActivity = new Intent(this, AccountActivity.class);
            startActivity(openAccountActivity);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        switch (id){

            case ID_PACKAGES_BY_PRICE_LOADER:

                Uri packagesByPriceQueryUri = PackagesContract.PackageEntry.CONTENT_URI;

                String sortOrder = PackagesContract.PackageEntry.COLUMN_PACKAGE_BASE_PRICE + " " +
                        "ASC";

                return new CursorLoader(this,
                        packagesByPriceQueryUri,
                        null,
                        null,
                        null,
                        sortOrder);

            case ID_PACKAGES_BY_DATE_LOADER:

                Uri packagesByDateQueryUri = PackagesContract.PackageEntry.CONTENT_URI;

                String sortOrderDate = PackagesContract.PackageEntry.COLUMN_PACKAGE_START_DATE +
                        " ASC";

                return new CursorLoader(this,
                        packagesByDateQueryUri,
                        null,
                        null,
                        null,
                        sortOrderDate);


            default:
                throw new RuntimeException("Loader not implemented " + id);

        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mCallback.OnPackagesSorted(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    public void setmCallback(OnPackagesSortedListener mCallback) {
        this.mCallback = mCallback;
    }
}
