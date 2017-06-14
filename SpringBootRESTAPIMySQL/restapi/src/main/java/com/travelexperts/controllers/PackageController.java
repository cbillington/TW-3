package com.travelexperts.controllers;


import com.travelexperts.Data;
import com.travelexperts.RestClient;
import com.travelexperts.TestDataUtil;
import com.travelexperts.WatchlistRequest;
import com.travelexperts.entities.*;
import com.travelexperts.entities.Package;
import com.travelexperts.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

@RestController
public class PackageController {
    private PackageRepository mPackageRepository;

    @Autowired
    private WatchlistedPackagesRepository mWatchlistedRepo;

    @Autowired
    public PackageController(PackageRepository mPackageRepository) {
        this.mPackageRepository = mPackageRepository;
    }

    @Autowired
    public PackageTestRepository mPackageTestRepository;


    /**
     *
     * getAllPackages is called when http GET request is sent to .../packages.
     * @return: getAllPackages returns a List of Package entities taken from the DB.
     *
     */
    @RequestMapping(value = "/packages", method = RequestMethod.GET)
    public PackagesJSON getAllPackages(){

        List<PackageTest> packages = mPackageTestRepository.findAll(); // gathers list of package entities
        return new PackagesJSON(packages); // PackagesJSON wraps the list in a nice container for the response body.
    }

    /**
     * updatePackage is called when a http POST request is sent to the endpoint .../packages.
     * @param packageToUpdate: updatePackage requires a Package entity to update to be in the request body in JSON format.
     *                       Jackson+Spring is converting the JSON body into a entity to be saved.
     * @return: updatePackage returns a string //TODO: update return to be something significant.
     */
    @RequestMapping(value = "/packages", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updatePackage(@RequestBody PackageTest packageToUpdate){

        /*
            When a package is updated we notify the mobile application with the packageid.

         */
        WatchlistRequest watchlistRequest = new WatchlistRequest(); // request object for Firebase Cloud Messaging.
        Data data = new Data(); // data to be sent with the request
        data.setPackageId(packageToUpdate.getPackageId()); // package id that was updated ( used by the mobile app to see if the user has watchlisted this package.)
        watchlistRequest.setData(data);

        // the To parameter of the request is which device to send the message to. //TODO: refactor To to be automatic instead of static. (can get device id on mobnile app --> save to DB)
        watchlistRequest.setTo("cErNLIXtW3g:APA91bG1IC_Bp8VC8qWOOSswZ9goC5CX64LFIFQURsyinbWMVzkfG3ifEMe0g-NEY3XSPaDvcWlEC7twluoylSsqOv8O7siuRQ2jBVxGvobjWh_0afLZRrC2-3c4vDfvnO4WLlG6SGb-");

        mPackageTestRepository.save(packageToUpdate); // save the package to the DB.

        // create a new rest client and send the request to FCM which will relay to the mobile app.
        RestClient client = new RestClient();
        client.getApiService().updatePackage(RestClient.content_type, RestClient.auth, watchlistRequest).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("responded");
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });

        return "hello";
    }

    /**
     * updateWatchlist is called when http POST endpoint .../packages/watchlist is called. This method retrieves the package that was
     * watchlisted from the body and updates the proper package.
     * @param watchlistRowToUpdate : package watchlisted counter to be updated.
     * @return: returns a string //TODO: update return value to be somethign significant.
     *
     */
    @RequestMapping(value = "/packages/watchlist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateWatchlist(@RequestBody WatchlistedPackages watchlistRowToUpdate){
        WatchlistedPackages wp = mWatchlistedRepo.findByPackageId(watchlistRowToUpdate.getPackageId()); // find which row was being updated.

        if (wp != null){
            /*
                if the package to be updated is not null then it exists in the DB already. In this case simply update the counter.
                //TODO: implement decrementing if necessary.
             */
            wp.setNumWatchlisted(wp.getNumWatchlisted() + 1);
            mWatchlistedRepo.save(wp);
        }
        else{
            /*
                Otherwise the package is being watchlisted for the first time.
             */
            WatchlistedPackages wp2 = new WatchlistedPackages();
            wp2.setPackageId(watchlistRowToUpdate.getPackageId());
            wp2.setNumWatchlisted(1);
            mWatchlistedRepo.save(wp2);
        }




        return "hello";
    }

}
