package com.travelexperts;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;



/**
 *
 *  Interface IRestEndpoint is used with Retrofit to provide signatures for http endpoints (base is made when a retrofit object is created).
 *
 * */
public interface IRestEndpoint {

    /**
     * updatedPackage is used to update a package using a post request to .../
     * @param contentType: media type contained in the body.
     * @param authorization: auth for FCM.
     * @param request: request object containing the package Id to be updated.
     * @return: returns a Call object used by retrofit to gather what was returned by the request.
     *
     */
    @POST("send")
    Call<String> updatePackage(@Header("Content-Type")String contentType, @Header("Authorization") String authorization, @Body WatchlistRequest request);
}
