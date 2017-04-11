package com.travelexperts.travelpackages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.travelexperts.travelpackages.adapters.PackageTabPagerAdapter;
import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.data.PackagesDbHelper;
import com.travelexperts.travelpackages.fragments.PackageSearchFragment;
import com.travelexperts.travelpackages.network.Customer;
import com.travelexperts.travelpackages.sync.NetworkTasks;
import com.travelexperts.travelpackages.sync.OnPackagesSortedListener;
import com.travelexperts.travelpackages.utils.PreferenceUtils;

import org.w3c.dom.Text;

public class NavigationTestActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoaderManager
        .LoaderCallbacks<Cursor>, SearchView.OnQueryTextListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final int ID_PACKAGES_QUERY_LOADER = 300;
    public static final String TEXT_CHANGED_KEY = "text-changed-key";
    private OnPackagesSortedListener mCallback;

    private ViewPager mPackageTabViewPager;
    public static final String SORT_BY_PRICE = "sort-by-price";

    private static final int ID_PACKAGES_BY_PRICE_LOADER = 100;
    private static final int ID_PACKAGES_BY_DATE_LOADER = 200;
    private View headerView;
    private TextView customerName;
    private TextView customerEmail;

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


        PackagesDbHelper db = new PackagesDbHelper(this);
        db.getWritableDatabase();
        db.close();
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

        headerView = navigationView.getHeaderView(0);
        customerName = (TextView)headerView.findViewById(R.id.tv_customer_name);
        customerEmail = (TextView)headerView.findViewById(R.id.tv_customer_email);

        NetworkTasks.getCustomerFromNetwork(this, 143);
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);

        Log.d("hello", FirebaseInstanceId.getInstance().getToken());
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
        final SearchView searchItem = (SearchView)menu.findItem(R.id.action_search).getActionView();

        searchItem.setOnQueryTextListener(this);
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
        if (id == R.id.action_search_fragment){
            Intent openSearchActivity = new Intent(this, PackageSearchActivity.class);
            startActivity(openSearchActivity);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id){

            case R.id.nav_profile:
                Intent openAccountActivity = new Intent(this, AccountActivity.class);
                startActivity(openAccountActivity);
                break;

            case R.id.nav_search_packages:
                Intent openSearchActivity = new Intent(this, PackageSearchActivity.class);
                startActivity(openSearchActivity);
                break;

            case R.id.nav_purchases:
                Intent openBookingsActivity = new Intent(this, BookingsActivity.class);
                startActivity(openBookingsActivity);
                break;

            case R.id.nav_watchlisted_packages:
                Intent openWatchlistedPackages = new Intent(this, WatchlistedPackagesActivity
                        .class);
                startActivity(openWatchlistedPackages);
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



            case ID_PACKAGES_QUERY_LOADER:
                String query = args.getString(TEXT_CHANGED_KEY);
                Log.d("query", query);
                Uri packagesBySearchQueryUri = PackagesContract.PackageEntry.CONTENT_URI;
                String packageNameColumn = PackagesContract.PackageEntry.COLUMN_PACKAGE_NAME;

                String whereClause = "PkgName LIKE ?";
                String[] selectionClause = {"%" + query + "%"};

                if (query.isEmpty() || query.equals("empty")){

                    return new CursorLoader(this,
                            packagesBySearchQueryUri,null, null, null, null);
                }
                else {
                    return new CursorLoader(this,
                            packagesBySearchQueryUri,
                            null,
                            whereClause,
                            selectionClause,
                            null);
                }

            default:
                throw new RuntimeException("Loader not implemented " + id);

        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d("hello", String.valueOf(data.getCount()));
        mCallback.OnPackagesSorted(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    public void setmCallback(OnPackagesSortedListener mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String queryText = newText;

        if(newText.isEmpty()){

            queryText = "empty";
        }

        Bundle textChanged = new Bundle();
        textChanged.putString(TEXT_CHANGED_KEY, queryText);
        Log.d("bundle args", textChanged.getString(TEXT_CHANGED_KEY));


        getSupportLoaderManager().restartLoader(ID_PACKAGES_QUERY_LOADER, textChanged, this);



        return true;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(PreferenceUtils.KEY_CUSTOMER)){
            Customer cust = PreferenceUtils.getCustomer(this);
            customerName.setText(cust.getCustFirstName()+ " " + cust.getCustLastName());
            customerEmail.setText(cust.getCustEmail());
        }
    }
}
