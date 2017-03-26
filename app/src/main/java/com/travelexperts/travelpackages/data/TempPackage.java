package com.travelexperts.travelpackages.data;

import com.travelexperts.travelpackages.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 744095 on 3/24/2017.
 */

public class TempPackage {
    public String packagename;
    public String description;
    public int photoId;


    TempPackage(String name, String description, int photoId) {
        this.packagename = name;
        this.description = description;
        this.photoId = photoId;
    }

    private List<TempPackage> temppackages;

    private void initializeData() {
        temppackages = new ArrayList<>();
        temppackages.add(new TempPackage("Caribbean cruise", "Enjoy a cruise to the Carribean", R.drawable.caribbean));
        temppackages.add(new TempPackage("American Getaway", "Travel anywhere in north america", R.drawable.demo));
        temppackages.add(new TempPackage("Island Paradise", "Enjoy a getaway to an Paradise", R.drawable.island));
    }

}