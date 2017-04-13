package com.travelexperts.travelpackages.sync;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.data.PackagesDbHelper;
import com.travelexperts.travelpackages.network.CallbackUtils;
import com.travelexperts.travelpackages.network.Customer;
import com.travelexperts.travelpackages.network.IRestCallback;
import com.travelexperts.travelpackages.network.Package;
import com.travelexperts.travelpackages.network.PackagesWrapper;
import com.travelexperts.travelpackages.network.RestClient;
import com.travelexperts.travelpackages.utils.Logger;
import com.travelexperts.travelpackages.utils.PreferenceUtils;

import java.util.List;

import retrofit2.Response;

/**
 * Created by 468364 on 3/29/2017.
 */

public class NetworkTasks {

    private static final String TAG = NetworkTasks.class.getSimpleName();

    public static final String ACTION_GET_PACKAGES_FROM_NETWORK = "get-packages-from-network";
    public static final String ACTION_GET_CUSTOMER_FROM_NETWORK = "get-customer-from-network";
    public static final String ACTION_UPDATE_CUSTOMER_FROM_NETWORK = "update-customer-from-network";

    public static final String CUSTOMER_ID_KEY = "customer-id-key";

    public static void executeTask(Context context, String action, @Nullable Bundle extras) {

        if(action.equals(ACTION_GET_PACKAGES_FROM_NETWORK)){
            getPackagesFromNetwork(context);
        }
        else if(action.equals(ACTION_GET_CUSTOMER_FROM_NETWORK)){
            int customerId = extras.getInt(CUSTOMER_ID_KEY);
            getCustomerFromNetwork(context, customerId);
        }
    }

    public static void getCustomerFromNetwork(final Context context, int customerId) {
        RestClient restClient = new RestClient();


        // interface for dealing with the response from an asynchronous api call.
        IRestCallback customerCallback = new IRestCallback() {
            @Override
            public void onSuccess(Response response) {
                /*
                    If the call was successful, then the response has what you need.
                 */
                Customer customerFromNetwork = (Customer)response.body();
                Log.d(TAG, customerFromNetwork.toString());
                PreferenceUtils.updateCustomer(context, customerFromNetwork);
                Log.d("NetworkTasks", customerFromNetwork.getCustAddress());

            }
        };

        // Pass the call and callback instance to a utility.

        CallbackUtils.requestCustomerFromNetwork(restClient.getApiService().getCustomer
                        (customerId),
                customerCallback);
    }

    public static void getPackagesFromNetwork(final Context context) {
        RestClient restClient = new RestClient();


        // interface for dealing with the response from an asynchronous api call.
        IRestCallback packagesCallback = new IRestCallback() {
            @Override
            public void onSuccess(Response response) {
                /*
                    If the call was successful, then the response has what you need.
                 */
                PackagesWrapper pw = (PackagesWrapper) response.body();
                List<Package> packages = pw.getItems();
                Log.d("hello", String.valueOf(packages.size()));
                ContentValues [] contentValues = new ContentValues[packages.size()];
                PackagesDbHelper dbHelper = new PackagesDbHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("DELETE FROM " + PackagesContract.PackageEntry.TABLE_NAME);
                for(int i = 0; i < packages.size(); i++){
                    Log.d("hello", packages.get(i).toString());
                    contentValues[i] = packages.get(i).getContentValues();
                }

                int numRowsInserted = context.getContentResolver().bulkInsert(PackagesContract.PackageEntry
                        .CONTENT_URI, contentValues);
                Log.d("rows inserted", String.valueOf(numRowsInserted));

            }
        };

        // Pass the call and callback instance to a utility.
        Log.d("hello", "I made it to the getPackagesFromNetwork method");
        CallbackUtils.requestPackagesFromCall(restClient.getApiService().getPackages(), packagesCallback);

    }

    public static void pushCustomerUpdate(final Context context, Customer customerToUpdate){

        RestClient restClient = new RestClient();

        IRestCallback customerUpdateCallback = new IRestCallback() {
            @Override
            public void onSuccess(Response response) {
                Log.d("NetworkTasks", "reached success");
            }
        };

        CallbackUtils.updateCustomerFromCall(restClient.getApiService().updateCustomer
                ("application/json", customerToUpdate), customerUpdateCallback);
    }
}
