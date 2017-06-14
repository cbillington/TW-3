package com.travelexperts.entities;

import java.util.List;

/**
 * Created by 468364 on 3/28/2017.
 */
public class PackagesJSON {
    private List<PackageTest> items;

    public PackagesJSON(List<PackageTest> items) {
        this.items = items;
    }

    public List<PackageTest> getItems() {
        return items;
    }

    public void setItems(List<PackageTest> items) {
        this.items = items;
    }
}
