package com.emsi.pfe.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
@FeignClient(url = "https://announces-microserver-production.up.railway.app", path = "/",name = "announcements-app")
public interface AnnouncementRestClient {
    @PutMapping(value = "announces/removePlace/{announcementPublicId}")
    void cancelPassengerSeatBooking(@PathVariable String announcementPublicId);

    @PutMapping(value = "announces/addPlace/{announcementPublicId}")
    void bookPassengerSeat(@PathVariable String announcementPublicId);
}