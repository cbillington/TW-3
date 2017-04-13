package com.travelexperts.travelpackages;

import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.fragments.BookingDialogFragment;
import com.travelexperts.travelpackages.utils.CursorUtils;
import com.travelexperts.travelpackages.network.Package;
import com.travelexperts.travelpackages.utils.DateUtils;
import com.travelexperts.travelpackages.utils.PreferenceUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackageDetailsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, View.OnClickListener {


    private Cursor mPackageCursor;
    private Package mPackage;
    private TextView mPackageNameTextView;
    private TextView mPackagePriceTextView;
    private TextView mPackageDateTextView;
    private TextView mPackageDescriptionTextView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout mToolBarLayout;
    private Button mBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolBarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String packageId = getIntent().getStringExtra("PackageId");

        mPackageCursor = CursorUtils.getPackageById(packageId, this);
        mPackageCursor.moveToFirst();

        mPackageNameTextView = (TextView)findViewById(R.id.tv_package_name);
        mPackagePriceTextView = (TextView)findViewById(R.id.tv_package_price);
        mPackageDateTextView = (TextView)findViewById(R.id.textView8);
        mPackageDescriptionTextView = (TextView)findViewById(R.id.textView17);
        mBookButton = (Button)findViewById(R.id.button2);
        mBookButton.setOnClickListener(this);
        ImageView photo = (ImageView)findViewById(R.id.iv_package_photo_big);

        String url = mPackageCursor.getString(mPackageCursor.getColumnIndex(PackagesContract
                .PackageEntry.COLUMN_PACKAGE_IMAGE_URL));

        Glide.with(this).load(url).into(photo);

        fillPackage();
        setViews();


        // get reference to map fragment
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment_maps);
        mapFragment.getMapAsync(this);

        // get the lat/lng for the destination



    }

    private LatLng getLatLng(String pkgName) {
        String locationSearchText = pkgName.split(" ")[0];
        Log.d("location", locationSearchText);

        Geocoder gc = new Geocoder(this);

        List<Address> adresses = new ArrayList<>();
        try {
            adresses = gc.getFromLocationName(locationSearchText, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new LatLng(adresses.get(0).getLatitude(), adresses.get(0).getLongitude() );
    }

    private void setViews() {
        mPackageNameTextView.setText(mPackage.getPkgName());
        mPackagePriceTextView.setText("$" + String.valueOf(mPackage.getPkgBasePrice()));
        // make package date
        String pkgStartDate = DateUtils.fromMilliToFormatted(mPackage.getPkgStartDate());
        String pkgEndDate = DateUtils.fromMilliToFormatted(mPackage.getPkgEndDate());
        mPackageDateTextView.setText(pkgStartDate + " - " + pkgEndDate);

        mPackageDescriptionTextView.setText(mPackage.getPkgDesc());
        mToolBarLayout.setTitle(mPackage.getPkgName());
    }

    private void fillPackage() {
        String pkgName = mPackageCursor.getString(mPackageCursor.getColumnIndex("PkgName"));
        // price
        String pkgPrice = mPackageCursor.getString(mPackageCursor.getColumnIndex("PkgBasePrice"));
        // start date
        String pkgStartDate = mPackageCursor.getString(mPackageCursor.getColumnIndex
                ("PkgStartDate"));
        // end date
        String pkgEndDate = mPackageCursor.getString(mPackageCursor.getColumnIndex("PkgEndDate"));
        //description
        String pkgDescription = mPackageCursor.getString(mPackageCursor.getColumnIndex("PkgDesc"));
        mPackage = new Package();
        mPackage.setPkgName(pkgName);
        mPackage.setPkgBasePrice(Double.valueOf(pkgPrice));
        mPackage.setPkgStartDate(Long.valueOf(pkgStartDate));
        mPackage.setPkgEndDate(Long.valueOf(pkgEndDate));
        mPackage.setPkgDesc(pkgDescription);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final LatLng cordsOfDestination = getLatLng(mPackage.getPkgName());

        // add the marker
        googleMap.addMarker(new MarkerOptions().position(cordsOfDestination).title("Destination"));

        //move the map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(cordsOfDestination));

        googleMap.setOnMapClickListener(this);

    }

    @Override
    public void onMapClick(LatLng latLng) {
        final LatLng cordsOfDestination = getLatLng(mPackage.getPkgName());
        Log.d("cords", cordsOfDestination.toString());
        Intent startMapActivity = new Intent(this, MapActivity.class);
        startMapActivity.putExtra("lat", cordsOfDestination.latitude);
        startMapActivity.putExtra("lon", cordsOfDestination.longitude);


        startActivity(startMapActivity);
    }

    @Override
    public void onClick(View v) {

        // on click --> open dialog fragment and pass the customer Id
        FragmentManager fm = getSupportFragmentManager();
        BookingDialogFragment bdf = new BookingDialogFragment();
        Bundle customerIdBundle = new Bundle();
        customerIdBundle.putString("customerId", String.valueOf(PreferenceUtils.getCustomer(this).getCustomerId()));
        customerIdBundle.putDouble("pkgBasePrice", mPackage.getPkgBasePrice());
        customerIdBundle.putLong("pkgStartDate", mPackage.getPkgStartDate());
        customerIdBundle.putLong("pkgEndDate", mPackage.getPkgEndDate());
        customerIdBundle.putString("pkgDesc", mPackage.getPkgName());
        bdf.setArguments(customerIdBundle);

        bdf.show(fm, "BookingDialog");
    }
}
