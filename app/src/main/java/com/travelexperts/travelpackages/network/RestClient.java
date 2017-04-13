package com.travelexperts.travelpackages.network;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Retrofit;


/**
 * Utility class used for building a retrofit instance.
 *
 */

public class RestClient {

    // base Url for rest api
    private static final String BASE_URL_STRING = "http://9427b667.ngrok.io/api/";
    // instance of the interface required for Retrofit -- used to define your path calls.
    private IRestEndpoint iRestEndpoint;

    public RestClient() {

        // object needed for jackson
        ObjectMapper objMapper = new ObjectMapper();

        // retrofit instance with base url and mapper object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_STRING)
                .addConverterFactory(new JacksonConverterFactory(objMapper))
                .build();

        // set the member variable of an instantiation of the endpoint interface.
        iRestEndpoint = retrofit.create(IRestEndpoint.class);
    }

    // getter for the rest endpoint interface.
    public IRestEndpoint getApiService(){
        Log.d("rest client made", "rest client made");
        return iRestEndpoint;
    }

}
