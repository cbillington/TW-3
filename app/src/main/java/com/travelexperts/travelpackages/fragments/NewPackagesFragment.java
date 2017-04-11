package com.travelexperts.travelpackages.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.travelexperts.travelpackages.R;
import com.travelexperts.travelpackages.network.Package;
import com.travelexperts.travelpackages.utils.PreferenceUtils;
import com.travelexperts.travelpackages.utils.WatchlistedPackages;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPackagesFragment extends Fragment implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {


    private Button mTestWatchlistedPackagesButton;
    private Package pkgToAppend;

    public NewPackagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_packages, container, false);
        mTestWatchlistedPackagesButton = (Button)v.findViewById(R.id.btn_test_watchlisted_packages);
        mTestWatchlistedPackagesButton.setOnClickListener(this);
        return v;
    }



    @Override
    public void onClick(View v) {

        // test the append package to watchlist
        pkgToAppend = new Package();
        pkgToAppend.setPackageId(100);
        pkgToAppend.setPkgName("Preference Watchlist Test");
        PreferenceUtils.appendPackageToWatchlist(pkgToAppend, getActivity());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(PreferenceUtils.KEY_WATCHLISTED_PACKAGES)){
            WatchlistedPackages watchlistedPackages = PreferenceUtils.getWatchlistedPackages(getActivity
                    ());

            Log.d("hello", String.valueOf(watchlistedPackages.getItems().size()));

            Log.d("hello", String.valueOf(watchlistedPackages.contains(pkgToAppend)));

        }
    }
}
