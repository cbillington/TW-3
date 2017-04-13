package com.travelexperts.travelpackages.fragments;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.travelexperts.travelpackages.BookingsActivity;
import com.travelexperts.travelpackages.R;
import com.travelexperts.travelpackages.network.Booking;
import com.travelexperts.travelpackages.network.BookingDetail;
import com.travelexperts.travelpackages.network.Customer;
import com.travelexperts.travelpackages.network.RestClient;
import com.travelexperts.travelpackages.sync.NetworkTasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by 468364 on 4/12/2017.
 */

public class BookingDialogFragment extends DialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner mNumberOfTravelsSpinner;
    private TextView mBookingTotalTextView;
    private Button mCancelButton;
    private Button mBookButton;
    private String mCustomerId;
    private Double mBookingDetailTotal;
    private double mPackageBasePrice;
    private ArrayList<Integer> integerArrayList;
    private double newTotal;
    private ArrayAdapter<Integer> integerArrayAdapter;
    private long mPackageStartDate;
    private long mPackageEndDate;
    private String mPackageDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mCustomerId = getArguments().getString("customerId", "badIdPassed");
        mPackageBasePrice = getArguments().getDouble("pkgBasePrice");
        mPackageStartDate = getArguments().getLong("pkgStartDate");
        mPackageEndDate = getArguments().getLong("pkgEndDate");
        mPackageDescription = getArguments().getString("pkgDesc");
        Log.d("customerId", mCustomerId);
        View rootView = inflater.inflate(R.layout.fragment_booking_dialog, container, false);
        // reference to views
        // number of travelers
        mNumberOfTravelsSpinner = (Spinner)rootView.findViewById(R.id.spinner_num_travlers);
        mBookingTotalTextView = (TextView)rootView.findViewById(R.id.tv_total);
        mCancelButton = (Button)rootView.findViewById(R.id.btn_cancel);
        mBookButton = (Button)rootView.findViewById(R.id.btn_book);

        // set listers for dissmiss/book
        mCancelButton.setOnClickListener(this);
        mBookButton.setOnClickListener(this);

        // setup interger array
        integerArrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

        // setup integer array adapter
        integerArrayAdapter = new ArrayAdapter<>(getActivity(), R
                .layout.support_simple_spinner_dropdown_item, integerArrayList);

        mNumberOfTravelsSpinner.setAdapter(integerArrayAdapter);
        mNumberOfTravelsSpinner.setOnItemSelectedListener(this);

        newTotal = mPackageBasePrice;

        return rootView;

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.btn_cancel){
            dismiss();
        }

        else{
            bookPackage();
        }
    }

    @TargetApi(21)
    private void bookPackage() {

        // need to make a Booking and a Booking Detail

        // make booking
        Integer bookingId = ThreadLocalRandom.current().nextInt(4000, 6000 + 1);
        Long bookingDate = System.currentTimeMillis();
        String bookingNo = "8D5FG";
        Double travelerCount = 0.5455;
        Integer spinnerItem = (Integer) mNumberOfTravelsSpinner.getSelectedItem();
        double travelerCountPrim = travelerCount.doubleValue();
        int spinnerItemPrim = spinnerItem.intValue();
        travelerCount = Double.valueOf(spinnerItemPrim);
        final Integer customerId = Integer.valueOf(mCustomerId);
        String tripTypeId = "B";
        Integer packageId = null;
        Object bookingDetail = null;
        Object fee = null;
        Object tripType = null;
        List<BookingDetail> bookingDetails = new ArrayList<>();


        // make booking detail
        Integer bookingDetailId = ThreadLocalRandom.current().nextInt(4000, 6000 + 1);
        Double itineraryNo = ThreadLocalRandom.current().nextDouble(4000, 6000 + 1);
        Long tripStart = mPackageStartDate;
        Long tripEnd = mPackageEndDate;
        String description = mPackageDescription;
        String destination = "";
        Double basePrice = Double.valueOf(mBookingTotalTextView.getText().toString().substring(1));
        Double agencyCommission = 100.50;
        Integer bookingDetailBookingId = bookingId;
        String regionId = "EU";
        String classId = "FST";
        String feeId = "NV";
        Integer productSupplierId = 75;


        // create the Booking
        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        booking.setBookingDate(bookingDate);
        booking.setBookingNo(bookingNo);
        booking.setTravelerCount(travelerCount);
        booking.setCustomerId(customerId);
        booking.setTripTypeId(tripTypeId);
        booking.setPackageId(packageId);
        booking.setBookingDetail(null);
        booking.setFee(null);
        booking.setTripType(null);
        booking.setBookingDetails(bookingDetails);


        // create booking detail
        BookingDetail bookingDetail1 = new BookingDetail();
        bookingDetail1.setBookingDetailId(bookingDetailId);
        bookingDetail1.setItineraryNo(itineraryNo);
        bookingDetail1.setTripStart(tripStart);
        bookingDetail1.setTripEnd(tripEnd);
        bookingDetail1.setDescription(description);
        bookingDetail1.setDestination(destination);
        bookingDetail1.setBasePrice(basePrice);
        bookingDetail1.setRegionId(regionId);
        bookingDetail1.setClassId(classId);
        bookingDetail1.setFeeId(feeId);
        bookingDetail1.setProductSupplierId(productSupplierId);
        bookingDetail1.setAgencyCommission(agencyCommission);
        bookingDetail1.setBookingId(bookingDetailBookingId);


        //add bookingDetail to booking list
        booking.getBookingDetails().add(bookingDetail1);


        RestClient restClient = new RestClient();


        restClient.getApiService().insertNewBooking("application/json", booking).enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {


            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {

            }
        });

        dismiss();
        Log.d("hello", "response reached");
        Intent openBookings = new Intent(getActivity(), BookingsActivity.class);
        getActivity().startActivity(openBookings);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        newTotal = mPackageBasePrice * integerArrayList.get(position);
        mBookingTotalTextView.setText("$" +String.valueOf(newTotal));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
