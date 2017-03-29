package com.travelexperts.travelpackages.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelexperts.travelpackages.R;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by 744095 on 3/20/2017.
 */

public class all_tab extends Fragment {
    private RecyclerView rvAllTab;
    private CardView cvAllTab;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState, Activity activity) {
        View rootView = inflater.inflate(R.layout.all_tab, container, false);

        //recyclerview
        /*rvAllTab = (RecyclerView) rootView.findViewById(R.id.rvAllTab);
        //LinearLayoutManager llm = new LinearLayoutManager(getContext());
        //rvAllTab.setLayoutManager(llm);


       // public List<getTempPackage> initializeData() {
            List<getTempPackage> temppackages;
            temppackages = new ArrayList<>();
            temppackages.add(new getTempPackage("Caribbean cruise", "Enjoy a cruise to the Carribean", R.drawable.caribbean));
            temppackages.add(new getTempPackage("American Getaway", "Travel anywhere in north america", R.drawable.demo));
            temppackages.add(new getTempPackage("Island Paradise", "Enjoy a getaway to an Paradise", R.drawable.island));
        //    return temppackages;
       // }
        RVAdapter adapter = new RVAdapter(temppackages);
        rvAllTab.setAdapter(adapter);

        return rootView;*/
        return rootView;
    }



}
