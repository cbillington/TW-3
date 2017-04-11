package com.travelexperts.travelpackages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Package_Details extends AppCompatActivity {
    TextView mPackageDescTextView;
    TextView mPackageStartDateTextView;
    TextView mPackageEndDateTextView;
    TextView mPackageNameTextView;
    TextView mPackagePriceTextView;
    ImageView mPackageImageView;
    private ImageView ivPackages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package__details);

        mPackageNameTextView = (TextView) findViewById(R.id.tvPackagePageName);
        mPackageDescTextView = (TextView) findViewById(R.id.tvPackagePageDescription);
        mPackageStartDateTextView = (TextView) findViewById(R.id.tvPackagePageStartDate);
        mPackageEndDateTextView = (TextView) findViewById(R.id.tvPackagePageEndDate);
        mPackagePriceTextView = (TextView) findViewById(R.id.tvPackagePagePrice);
        mPackageImageView = (ImageView) findViewById(R.id.ivPackagePageImage);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("Title");
        String price = bundle.getString("Price");
        String startDate = bundle.getString("Start Date");
        String endDate = bundle.getString("End Date");
        String description = bundle.getString("Description");
        String photo = bundle.getString("Photo");

        mPackageNameTextView.setText(title);
        mPackageDescTextView.setText(description);
        mPackageStartDateTextView.setText(startDate);
        mPackageEndDateTextView.setText(endDate);
        mPackagePriceTextView.setText(price);
        Glide.with(this).load(photo).into(mPackageImageView);
    }
}
