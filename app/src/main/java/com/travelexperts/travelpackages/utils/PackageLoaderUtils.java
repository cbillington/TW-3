package com.travelexperts.travelpackages.utils;

import android.os.Bundle;

/**
 * Created by 468364 on 4/8/2017.
 */

public class PackageLoaderUtils {
    // loader id
    public static int QUERY_PACKAGES_LOADER_ID = 100;

    //bundle keys
    public static String PACKAGE_DESTINATION_KEY = "package-destination-key";
    public static String PACKAGE_START_DATE_KEY = "package-start-date-key";
    public static String PACKAGE_PRICE_KEY = "package-price-key";

    /**
     * Create bundle to pass to loader args
     *
     * @param destination
     * @param startDateInMilli
     * @param price
     * @return
     */
    public static Bundle makeBundleForLoader(String destination, String startDateInMilli, String
            price){

        Bundle bundleToFill = new Bundle();
        bundleToFill.putString(PACKAGE_DESTINATION_KEY, destination);
        bundleToFill.putString(PACKAGE_START_DATE_KEY, startDateInMilli);
        bundleToFill.putString(PACKAGE_PRICE_KEY, price);

        return bundleToFill;
    }

}
