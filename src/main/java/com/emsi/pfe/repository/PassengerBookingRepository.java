package com.emsi.pfe.repository;

import com.emsi.pfe.entity.PassengerBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerBookingRepository extends JpaRepository<PassengerBooking,Long> {
}
