package com.travelexperts.travelpackages.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelexperts.travelpackages.R;

/**
 * Created by 744095 on 3/22/2017.
 */

public class favorited_packages_tab extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.favorited_packages_tab, container, false);
        return rootView;
    }

}
