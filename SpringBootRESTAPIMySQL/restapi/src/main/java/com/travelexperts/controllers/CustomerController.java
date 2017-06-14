package com.travelexperts.controllers;

import com.travelexperts.entities.Booking;
import com.travelexperts.entities.Booking2;
import com.travelexperts.entities.Customer;
import com.travelexperts.repositories.Booking2Repository;
import com.travelexperts.repositories.BookingRepository;
import com.travelexperts.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Repository of endpoints related to TravelExperts customers.
 *
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository mCustomerRepository; // JpA repository for Customer entities.

    @Autowired
    private BookingRepository mBookingRepo; // JpA repository for Booking entities.


    /**
     * updateCustomer is the method called when a POST request is sent to the endpoint ../customers, this endpoint is used to update
     * Customer information.
     * @param customer: customer parameter is a Customer entity object mapped directly from the request body by spring/jackson. It contains the information
     * about the customer that needs to be updated.
     * @return: In the response body the customer that was updated is returned.
     */
    @RequestMapping(value = "/customers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Customer updateCustomer(@RequestBody Customer customer){


        mCustomerRepository.save(customer);
        return customer;
    }

    /**
     * getCustomer is called on http GET request to .../customers/{id} endpoint is called. This method is used to return a requested customer by id from
     * the DB.
     * @param custId: integer customer id requested.
     * @return: returns a Customer entity from the id requested.
     */
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable(value = "id") String custId){
        return mCustomerRepository.findOne(Integer.valueOf(custId));
    }

    /**
     * insertBooking is called when a POST request is sent to endpoint .../bookings, this method inserts a new booking into the DB for that customer.
     * @param booking: JSON Booking entity objecty from the body of the request.
     * @return: returns a string //TODO: change the return message to something significant.
     */
    @RequestMapping(value = "/bookings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String insertBooking(@RequestBody Booking booking){

        mBookingRepo.save(booking);
        return "hello";
    }
}