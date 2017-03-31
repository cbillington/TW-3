package com.travelexperts.travelpackages.network;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRestEndpoint {

    @GET("packages")
    Call<PackagesWrapper> getPackages();

    @GET("customers/{id}")
    Call<Customer> getCustomer(@Path("id") int customerId);
}
