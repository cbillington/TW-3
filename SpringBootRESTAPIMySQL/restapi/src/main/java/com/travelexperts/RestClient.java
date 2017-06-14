package com.travelexperts;

import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Retrofit;

/**
 * RestClient is a wrapper object to make using Retrofit easier. This RestClient is used for interacting with FCM
 */
public class RestClient {

    // base Url for FCM
    private static final String BASE_URL_STRING = "https://fcm.googleapis.com/fcm/";

    // instance of the interface required for Retrofit -- used to define your path calls.
    private IRestEndpoint iRestEndpoint;

    public static final String auth = "key=AAAAoLp4s9A:APA91bHccz4QihFoIsf-N3T1mCQLch5xtsWp5xZhvqbfnzlci6BWx67Y51IRhAhwBCu3uegXWSR8JVHXnF8r7_Qdt3CYToZ7PcRfT2bvG2NIDp9LKJaqqytZTu1D5qBwdzwT2IsN8o6d";
    public static final String content_type = "application/json";

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
        return iRestEndpoint;
    }

}
