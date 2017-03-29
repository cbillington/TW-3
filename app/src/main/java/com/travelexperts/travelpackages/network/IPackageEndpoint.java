package com.travelexperts.travelpackages.network;


import retrofit2.Call;
import retrofit2.http.GET;

public interface IPackageEndpoint {

    @GET("packages")
    Call<PackagesJSON> getPackages();
}
