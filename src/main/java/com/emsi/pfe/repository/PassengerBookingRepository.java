package com.emsi.pfe.repository;

import com.emsi.pfe.entity.PassengerBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerBookingRepository extends JpaRepository<PassengerBooking,Long> {
    List<PassengerBooking> findByAnnouncementPublicId(String announcementPublicId);
    List<PassengerBooking> deleteByPassengerPublicIdAndAnnouncementPublicId(String passengerPublicId, String announcementPublicId);

    List<PassengerBooking> findByPassengerPublicIdAndAnnouncementPublicId(String publicId, String announcementPublicId);

}
