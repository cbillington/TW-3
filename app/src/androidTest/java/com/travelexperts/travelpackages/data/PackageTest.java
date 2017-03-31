package com.travelexperts.travelpackages.data;

import android.content.ContentValues;


import com.travelexperts.travelpackages.network.Package;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 468364 on 3/30/2017.
 */
public class PackageTest {

    private Package mPackage;


    @Before
    public void setUp() throws Exception {
        mPackage = new Package();
        mPackage.setPackageId(1);
        mPackage.setPkgAgencyCommission(15.5);
        mPackage.setPkgBasePrice(1.1);
        mPackage.setPkgDesc("hello");
        mPackage.setPkgEndDate((long) 1512321233);
        mPackage.setPkgStartDate((long) 1332423);
        mPackage.setPkgName("name");

    }

    @Test
    public void getContentValues() throws Exception {
        // this method tests whether the row returned for this package matches the number of fields
        // required in the content provider for a "package".
        ContentValues packageToInsert = mPackage.getContentValues();
        int numPropertiesInRow = packageToInsert.size();

        int numColumnsInTable = PackagesContract.PackageEntry.NUM_COLUMNS;

        String contentValuesError = "Returned content values does not match number of properties " +
                "in class";

        assertEquals(contentValuesError, numPropertiesInRow, numColumnsInTable);
    }

    @Test
    public void writeToParcel() throws Exception {

    }

}