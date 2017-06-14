package com.travelexperts.repositories;

import com.travelexperts.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 468364 on 4/12/2017.
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
