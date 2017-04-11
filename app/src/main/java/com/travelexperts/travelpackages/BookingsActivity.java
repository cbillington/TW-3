package com.travelexperts.travelpackages;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.travelexperts.travelpackages.network.Booking;
import com.travelexperts.travelpackages.network.Customer;
import com.travelexperts.travelpackages.utils.PreferenceUtils;

import java.util.List;

public class BookingsActivity extends AppCompatActivity {

    private Customer mCustomer;
    private List<Booking> mBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mCustomer = PreferenceUtils.getCustomer(this);
        mBookings = mCustomer.getBookings();

        Log.d("hello", String.valueOf(mBookings.size()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
