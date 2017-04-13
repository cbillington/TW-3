package com.travelexperts.travelpackages;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelexperts.travelpackages.adapters.BookingsAdapter;
import com.travelexperts.travelpackages.adapters.OnBookingClickListener;
import com.travelexperts.travelpackages.network.Booking;
import com.travelexperts.travelpackages.network.Customer;
import com.travelexperts.travelpackages.network.RestClient;
import com.travelexperts.travelpackages.sync.NetworkTasks;
import com.travelexperts.travelpackages.utils.PreferenceUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class BookingsActivityFragment extends Fragment implements OnBookingClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private Customer mCustomer;
    private List<Booking> mBookings;
    private RecyclerView mBookingsRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private List<Booking> mBookingsSortedByDate;
    private BookingsAdapter mBookingsAdapter;
    private ProgressDialog progress;
    public BookingsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bookings, container, false);

        RestClient newClient = new RestClient();

        progress = new ProgressDialog(getContext());
        progress.setMessage("Booking package...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
        NetworkTasks.getCustomerFromNetwork(getActivity(), 143);


        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .registerOnSharedPreferenceChangeListener(this);

        mBookingsRecyclerView = (RecyclerView)rootView.findViewById(R.id.rv_bookings);
        mBookingsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));

        mLayoutManager = new LinearLayoutManager(getActivity());
        mBookingsRecyclerView.setLayoutManager(mLayoutManager);
        mBookingsAdapter = new BookingsAdapter(getActivity(), null, this);
        mBookingsRecyclerView.setAdapter(mBookingsAdapter);



        return rootView;
    }

    private List<Booking> sortByDate(List<Booking> mBookings) {

        Collections.sort(mBookings, new Comparator<Booking>() {
            @Override
            public int compare(Booking o1, Booking o2) {
                if(o1.getBookingDate() < o2.getBookingDate()){
                    return 1;
                }
                else if (o1.getBookingDate().equals(o2.getBookingDate())){
                    return 0;
                }
                else{
                    return -1;
                }
            }
        });

        return mBookings;
    }

    @Override
    public void onBookingClicked(int bookingId) {
        Log.d("hello", "booking id: " + bookingId);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(PreferenceUtils.KEY_CUSTOMER)){
            mCustomer = PreferenceUtils.getCustomer(getActivity());
            mBookings = mCustomer.getBookings();
            mBookingsSortedByDate = sortByDate(mBookings);
            mBookingsAdapter.swapBookings(mBookingsSortedByDate);
            progress.dismiss();
        }
    }
}
