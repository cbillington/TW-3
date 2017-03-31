package com.travelexperts.travelpackages.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelexperts.travelpackages.network.Customer;

import java.io.IOException;

/**
 * Created by 468364 on 3/29/2017.
 */

public final class PreferenceUtils {

    /**
     * This utility is used to interact with SharedPreferences
     *
     */


    private static final String KEY_CUSTOMER = "customer-preference-key";


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

}
