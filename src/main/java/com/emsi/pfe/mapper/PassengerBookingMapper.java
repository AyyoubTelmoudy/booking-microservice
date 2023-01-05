package com.emsi.pfe.mapper;

import com.emsi.pfe.dto.PassengerBookingDTO;
import com.emsi.pfe.entity.PassengerBooking;
import org.springframework.stereotype.Component;

@Component
public class PassengerBookingMapper {
    public PassengerBookingDTO toPassengerBookingDTO(PassengerBooking passengerBooking) {
        PassengerBookingDTO  passengerBookingDTO=new PassengerBookingDTO();
        passengerBookingDTO.setConfirmed(passengerBooking.isConfirmed());
        passengerBookingDTO.setPassengerPublicId(passengerBooking.getPassengerPublicId());
        passengerBookingDTO.setDate(passengerBooking.getDate());
        passengerBookingDTO.setAnnouncementPublicId(passengerBooking.getAnnouncementPublicId());
        return  passengerBookingDTO;
    }
}
