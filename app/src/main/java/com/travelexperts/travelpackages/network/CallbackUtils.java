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
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(Call<PackagesWrapper> call, Throwable t) {
                Log.d("hello", t.getMessage());
            }
        });
    }
}
