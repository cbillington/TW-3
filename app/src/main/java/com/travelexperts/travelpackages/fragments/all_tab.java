package com.travelexperts.travelpackages.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelexperts.travelpackages.R;
import com.travelexperts.travelpackages.RVAdapter;
import com.travelexperts.travelpackages.data.TempPackage;


/**
 * Created by 744095 on 3/20/2017.
 */

public class all_tab extends Fragment {
    private RecyclerView rvAllTab;
    private CardView cvAllTab;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.all_tab, container, false);

        //recyclerview
        rvAllTab = (RecyclerView) rootView.findViewById(R.id.rvAllTab);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvAllTab.setLayoutManager(llm);


        RVAdapter adapter = new RVAdapter(TempPackage);
        rvAllTab.setAdapter(adapter);


        /*rvAllTab.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAllTab.setHasFixedSize(true);
        rvAllTab.setAdapter(new Adapter(getContext(), items));*/

        //CardView


        return rootView;
    }



}
