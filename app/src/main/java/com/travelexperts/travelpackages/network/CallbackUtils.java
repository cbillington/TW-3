package com.travelexperts.travelpackages.network;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 468364 on 3/28/2017.
 */

public class CallbackUtils {


    public static void requestPackagesFromCall(Call<PackagesWrapper> call, final IRestCallback
            callback) {


        call.enqueue(new Callback<PackagesWrapper>() {
            @Override
            public void onResponse(Call<PackagesWrapper> call, Response<PackagesWrapper> response) {
                Log.d("hello", "I made it to the onResponse");
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<PackagesWrapper> call, Throwable t) {
                Log.d("hello", t.getMessage());
            }
        });
    }

    public static void requestCustomerFromNetwork(Call<Customer> call, final IRestCallback
            callback) {
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {

                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.d("hello", t.getMessage());
            }
        });
    }

    public static void updateCustomerFromCall(Call<Customer> customerCall, final IRestCallback customerUpdateCallback) {

        customerCall.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {

                Log.d("CallbackUtils", String.valueOf(response.code()));
                customerUpdateCallback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Log.d("CallbackUtils", "failure");
                t.printStackTrace();
            }
        });
    }
}
