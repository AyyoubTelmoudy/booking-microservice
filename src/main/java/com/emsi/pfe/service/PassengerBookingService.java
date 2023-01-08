package com.emsi.pfe.service;

import com.emsi.pfe.dto.PassengerBookingDTO;
import com.emsi.pfe.dto.PassengerDTO;
import com.emsi.pfe.exception.ReservationAlreadyDoneException;
import com.emsi.pfe.requests.BookingRequest;

import java.util.List;

public interface PassengerBookingService {
    List<PassengerDTO> getPassengersByAnnouncement(String idAnnouncement);
    PassengerBookingDTO bookPassengerSeat(String announcementPublicId) throws ReservationAlreadyDoneException;
    void cancelPassengerSeatBooking(String announcementPublicId);
    void confirmReservation(String announcementPublicId);
}
