package com.travelexperts.travelpackages.sync;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.network.CallbackUtils;
import com.travelexperts.travelpackages.network.IRestCallback;
import com.travelexperts.travelpackages.network.Package;
import com.travelexperts.travelpackages.network.PackagesWrapper;
import com.travelexperts.travelpackages.network.RestClient;

import java.util.List;

import retrofit2.Response;

/**
 * Created by 468364 on 3/29/2017.
 */

public class PackageTasks {

    public static final String ACTION_GET_PACKAGES_FROM_NETWORK = "get-packages-from-network";


    public static void executeTask(Context context, String action) {

        if(action.equals(ACTION_GET_PACKAGES_FROM_NETWORK)){
            getPackagesFromNetwork(context);
        }
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

                for(int i = 0; i < packages.size(); i++){
                    contentValues[i] = packages.get(i).getContentValues();
                }

                int numRowsInserted = context.getContentResolver().bulkInsert(PackagesContract.PackageEntry
                        .CONTENT_URI, contentValues);
                Log.d("hello", String.valueOf(numRowsInserted));

            }
        };

        // Pass the call and callback instance to a utility.
        Log.d("hello", "I made it to the getPackagesFromNetwork method");
        CallbackUtils.requestPackagesFromCall(restClient.getApiService().getPackages(), packagesCallback);

    }
}
