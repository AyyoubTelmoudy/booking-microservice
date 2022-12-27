package com.emsi.pfe.controller;

import com.emsi.pfe.dto.PassengerBookingDTO;
import com.emsi.pfe.dto.PassengerDTO;
import com.emsi.pfe.service.PassengerBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping
public class PassengerBookingController {

    @Autowired
    PassengerBookingService passengerBookingService;

    @GetMapping(value = "/booking/announcements/{announcementPublicId}/passengers")
    public List<PassengerDTO> getPassengersByAnnouncement(@PathVariable(name = "announcementPublicId") String announcementPublicId)
    {
        return passengerBookingService.getPassengersByAnnouncement(announcementPublicId);
    }

    @PostMapping(value = "/booking/announcements/{announcementPublicId}")
    public PassengerBookingDTO bookPassengerSeat(@PathVariable String announcementPublicId)
    {
        return passengerBookingService.bookPassengerSeat(announcementPublicId);
    }

    @DeleteMapping(value = "/booking/announcements/{announcementPublicId}")
    public void cancelPassengerSeatBooking(@PathVariable String announcementPublicId)
    {
        passengerBookingService.cancelPassengerSeatBooking(announcementPublicId);
    }



}
