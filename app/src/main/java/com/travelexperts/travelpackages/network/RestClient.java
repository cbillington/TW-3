package com.travelexperts.travelpackages.network;

import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Retrofit;

/**
 * Created by 468364 on 3/28/2017.
 */

public class RestClient {

    private static final String BASE_URL_STRING = "http:10.0.2.2:8080/api/";
    private IRestEndpoint iRestEndpoint;

    public RestClient() {

        ObjectMapper objMapper = new ObjectMapper();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_STRING)
                .addConverterFactory(new JacksonConverterFactory(objMapper))
                .build();

        iRestEndpoint = retrofit.create(IRestEndpoint.class);
    }

    public IRestEndpoint getApiService(){
        return iRestEndpoint;
    }

}
