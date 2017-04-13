package com.travelexperts.travelpackages;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private LatLng mLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);


        Double lat = getIntent().getDoubleExtra("lat", 0);
        Double lng = getIntent().getDoubleExtra("lon", 0);


        mLatLng = new LatLng(lat, lng);
        Log.d("coords", mLatLng.toString());
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(mLatLng).title("Destination"));

        //move the map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLng));
    }
}
