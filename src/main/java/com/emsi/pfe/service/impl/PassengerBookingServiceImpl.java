package com.emsi.pfe.service.impl;

import com.emsi.pfe.dto.PassengerBookingDTO;
import com.emsi.pfe.dto.PassengerDTO;
import com.emsi.pfe.entity.PassengerBooking;
import com.emsi.pfe.exception.ReservationAlreadyDoneException;
import com.emsi.pfe.feign.AccountRestClient;
import com.emsi.pfe.feign.AnnouncementRestClient;
import com.emsi.pfe.mapper.PassengerBookingMapper;
import com.emsi.pfe.repository.PassengerBookingRepository;
import com.emsi.pfe.security.SecurityConstants;
import com.emsi.pfe.security.SecurityUtils;
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
    SecurityUtils securityUtils;
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
           passengerDTO.setConfirmed(booking.isConfirmed());
           passengerDTOS.add(passengerDTO);
        }
        return passengerDTOS;
    }

    @Override
    public PassengerBookingDTO bookPassengerSeat(String announcementPublicId) throws ReservationAlreadyDoneException {
        String passengerPublicId=accountRestClient.getPassengerByEmail(securityUtils.getCurrentUserEmail()).getPublicId();
        PassengerBooking oldPassengerBooking=passengerBookingRepository.findByPassengerPublicIdAndAnnouncementPublicId(passengerPublicId,announcementPublicId);
        if(oldPassengerBooking!=null)
        {
            throw new ReservationAlreadyDoneException(SecurityConstants.RESERVATION_ALREADY_DONE);
        }
        else
        {
            PassengerBooking passengerBooking=new PassengerBooking();
            passengerBooking.setDate(new Date());
            passengerBooking.setAnnouncementPublicId(announcementPublicId);
            passengerBooking.setPassengerPublicId(passengerPublicId);
            passengerBooking.setPublicId(Utils.genereteRandomString(32));
            passengerBooking.setConfirmed(false);
            passengerBooking=passengerBookingRepository.save(passengerBooking);
            return passengerBookingMapper.toPassengerBookingDTO(passengerBooking);
        }

    }

    @Override
    public void cancelPassengerSeatBooking(String announcementPublicId, String passengerPublicId) {
        passengerBookingRepository.deleteByPassengerPublicIdAndAnnouncementPublicId(passengerPublicId,announcementPublicId);
    }

    @Override
    public void confirmReservation(String announcementPublicId, String passengerPublicId) {
        PassengerBooking passengerBooking = passengerBookingRepository.findByPassengerPublicIdAndAnnouncementPublicId(passengerPublicId,announcementPublicId);
          if(passengerBooking!=null)
          {
              passengerBooking.setConfirmed(true);
              passengerBookingRepository.save(passengerBooking);
              announcementRestClient.bookPassengerSeat(announcementPublicId);
          }
    }
}
