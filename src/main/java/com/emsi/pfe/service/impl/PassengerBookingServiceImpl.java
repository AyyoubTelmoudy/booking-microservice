package com.emsi.pfe.service.impl;

import com.emsi.pfe.dto.PassengerBookingDTO;
import com.emsi.pfe.dto.PassengerDTO;
import com.emsi.pfe.entity.PassengerBooking;
import com.emsi.pfe.feign.AccountRestClient;
import com.emsi.pfe.mapper.PassengerBookingMapper;
import com.emsi.pfe.repository.PassengerBookingRepository;
import com.emsi.pfe.requests.BookingRequest;
import com.emsi.pfe.service.PassengerBookingService;
import com.emsi.pfe.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PassengerBookingServiceImpl implements PassengerBookingService {

    @Autowired
    AccountRestClient accountRestClient;
    @Autowired
    PassengerBookingRepository passengerBookingRepository;
    @Autowired
    PassengerBookingMapper passengerBookingMapper;
    @Override
    public List<PassengerDTO> getPassengersByAnnouncement(String idAnnouncement) {
        List<PassengerBooking> passengerBookings=passengerBookingRepository.findByAnnouncementPublicId(idAnnouncement);
        List<PassengerDTO> passengerDTOS=new ArrayList<PassengerDTO>();
        for (PassengerBooking booking : passengerBookings)
        {
           PassengerDTO passengerDTO=accountRestClient.getPassengerByPublicId(booking.getPassengerPublicId());
           passengerDTOS.add(passengerDTO);
        }
        return passengerDTOS;
    }

    @Override
    public PassengerBookingDTO bookPassengerSeat(BookingRequest bookingRequest) {
        PassengerBooking passengerBooking=new PassengerBooking();
        passengerBooking.setDate(new Date());
        passengerBooking.setAnnouncementPublicId(bookingRequest.getAnnouncementPublicId());
        passengerBooking.setPassengerPublicId(bookingRequest.getPassengerPublicId());
        passengerBooking.setPublicId(Utils.genereteRandomString(32));
        passengerBooking=passengerBookingRepository.save(passengerBooking);
        return passengerBookingMapper.toPassengerBookingDTO(passengerBooking);
    }
}
