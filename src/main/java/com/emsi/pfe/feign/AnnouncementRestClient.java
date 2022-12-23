package com.emsi.pfe.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://localhost:8081", path = "/",name = "announcements-app")
public interface AnnouncementRestClient {
    @PostMapping(value = "announcements/{announcementPublicId}")
    void cancelPassengerSeatBooking(@PathVariable String announcementPublicId);

    @DeleteMapping(value = "announcement/{announcementPublicId}")
    void bookPassengerSeat(@PathVariable String announcementPublicId);
}