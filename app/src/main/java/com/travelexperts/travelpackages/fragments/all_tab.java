package com.travelexperts.travelpackages.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelexperts.travelpackages.Adapter;
import com.travelexperts.travelpackages.R;
import com.travelexperts.travelpackages.data.PackagesDbHelper;


/**
 * Created by 744095 on 3/20/2017.
 */

public class all_tab extends Fragment {
    private RecyclerView rvAllTab;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;
    String[] items={"package 1", "package 2", "package 3"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.all_tab, container, false);

        //recyclerview
        rvAllTab = (RecyclerView) rootView.findViewById(R.id.rvAllTab);
        rvAllTab.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAllTab.setHasFixedSize(true);
        rvAllTab.setAdapter(new Adapter(getContext(), items));

        return rootView;
    }



}
