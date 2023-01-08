package com.emsi.pfe.feign;

import com.emsi.pfe.dto.PassengerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://accountmicroservice-production.up.railway.app", path = "/",name = "account")
public interface AccountRestClient {
    @GetMapping(value = "passengers/getbypublicid/{passengerPublicId}")
    PassengerDTO getPassengerByPublicId(@PathVariable String passengerPublicId);

    @GetMapping(value = "passengers/getbyemail/{email}")
    PassengerDTO getPassengerByEmail(@PathVariable String email);
}