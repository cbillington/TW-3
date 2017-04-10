package com.travelexperts.travelpackages.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelexperts.travelpackages.network.Customer;

import java.io.IOException;
import java.util.List;
import com.travelexperts.travelpackages.network.Package;
/**
 * Created by 468364 on 3/29/2017.
 */

public final class PreferenceUtils {

    /**
     *
     * This utility is used to interact with SharedPreferences
     *
     */


    public static final String KEY_CUSTOMER = "customer-preference-key";
    public static final String KEY_WATCHLISTED_PACKAGES = "key-watchlisted-packages";
    public static final String sDefaultWatchlistPackageJsonString = makeDefaultJsonString();

    private static String makeDefaultJsonString() {
        ObjectMapper objMapper = new ObjectMapper();
        WatchlistedPackages defaultWatchlistedPackages = new WatchlistedPackages();

        String stringToReturn = null;
        try {
            stringToReturn = objMapper.writeValueAsString(defaultWatchlistedPackages);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return stringToReturn;
    }

    public static void updateCustomer(Context context, Customer customer){
        // this method accesses the shared preferences and updates the customer info.
        ObjectMapper objMapper = new ObjectMapper();
        String customerJSON = null;
        try {
            customerJSON = objMapper.writeValueAsString(customer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(KEY_CUSTOMER, customerJSON);
        editor.apply();
    }

    public static Customer getCustomer(Context context){
        ObjectMapper objMapper = new ObjectMapper();
        Customer customerToReturn = null;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String customerJSON = sp.getString(KEY_CUSTOMER, null);
        try {
            customerToReturn = objMapper.readValue(customerJSON, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerToReturn;
    }

    public static WatchlistedPackages getWatchlistedPackages(Context context){
        ObjectMapper objMapper = new ObjectMapper();
        List<Package> packagesToReturn = null;
        WatchlistedPackages watchlistedPackages = null;

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String watchListedPackagesJson = sp.getString(KEY_WATCHLISTED_PACKAGES, null);

        try{
            watchlistedPackages = objMapper.readValue(watchListedPackagesJson, WatchlistedPackages
                    .class);
            packagesToReturn = watchlistedPackages.getItems();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return watchlistedPackages;
    }

    public static void appendPackageToWatchlist(Package packageToAppend, Context context){

        // get the watchlistedpackages object from preferences
        ObjectMapper objectMapper = new ObjectMapper();
        WatchlistedPackages watchlistedPackages = null;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String watchListedPackagesJson = sp.getString(KEY_WATCHLISTED_PACKAGES, sDefaultWatchlistPackageJsonString);

        try{
            watchlistedPackages = objectMapper.readValue(watchListedPackagesJson, WatchlistedPackages
                    .class);

            // append the new package
            watchlistedPackages.append(packageToAppend);
        }
        catch (IOException e){
            e.printStackTrace();
        }


        // re-add the wathclistedpackages object to preferences
        SharedPreferences.Editor editor = sp.edit();
        String watchlistedPackagesString = null;
        try {
            watchlistedPackagesString = objectMapper.writeValueAsString(watchlistedPackages);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        editor.putString(KEY_WATCHLISTED_PACKAGES, watchlistedPackagesString);
        editor.apply();

    }

}
