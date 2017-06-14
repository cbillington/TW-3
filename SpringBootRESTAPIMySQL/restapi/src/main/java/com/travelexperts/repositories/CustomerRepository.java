package com.travelexperts.repositories;

import com.travelexperts.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 468364 on 3/29/2017.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
