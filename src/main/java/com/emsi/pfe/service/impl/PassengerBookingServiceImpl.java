package com.emsi.pfe.service.impl;

import com.emsi.pfe.dto.PassengerBookingDTO;
import com.emsi.pfe.dto.PassengerDTO;
import com.emsi.pfe.entity.PassengerBooking;
import com.emsi.pfe.feign.AccountRestClient;
import com.emsi.pfe.feign.AnnouncementRestClient;
import com.emsi.pfe.mapper.PassengerBookingMapper;
import com.emsi.pfe.repository.PassengerBookingRepository;
import com.emsi.pfe.service.PassengerBookingService;
import com.emsi.pfe.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
@Transactional
@Service
public class PassengerBookingServiceImpl implements PassengerBookingService {

    @Autowired
    AccountRestClient accountRestClient;
    @Autowired
    AnnouncementRestClient announcementRestClient;
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
    public PassengerBookingDTO bookPassengerSeat(String announcementPublicId) {
        PassengerBooking passengerBooking=new PassengerBooking();
        passengerBooking.setDate(new Date());
        passengerBooking.setAnnouncementPublicId(announcementPublicId);
        passengerBooking.setPassengerPublicId(accountRestClient.getPassengerByEmail(Utils.getCurrentUserEmail()).getPublicId());
        passengerBooking.setPublicId(Utils.genereteRandomString(32));
        passengerBooking=passengerBookingRepository.save(passengerBooking);
        //announcementRestClient.bookPassengerSeat(announcementPublicId);
        return passengerBookingMapper.toPassengerBookingDTO(passengerBooking);
    }

    @Override
    public void cancelPassengerSeatBooking(String announcementPublicId) {
       List<PassengerBooking> passengerBookings= passengerBookingRepository.findByPassengerPublicIdAndAnnouncementPublicId(accountRestClient.getPassengerByEmail(Utils.getCurrentUserEmail()).getPublicId(),announcementPublicId);
       passengerBookingRepository.deleteByPassengerPublicIdAndAnnouncementPublicId(accountRestClient.getPassengerByEmail(Utils.getCurrentUserEmail()).getPublicId(),announcementPublicId);
        //announcementRestClient.cancelPassengerSeatBooking(announcementPublicId);
    }
}
