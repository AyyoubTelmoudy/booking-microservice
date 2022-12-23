package com.emsi.pfe.feign;

import com.emsi.pfe.dto.PassengerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(url = "https://accounts-microservice-production.up.railway.app", path = "/",name = "accounts-app")
public interface AccountRestClient {
    @GetMapping(value = "passengers/{passengerPublicId}")
    PassengerDTO getPassengerByPublicId(@PathVariable String passengerPublicId);

    @GetMapping(value = "passengers")
    PassengerDTO getPassengerByEmail(@RequestHeader Map<String, String> headerMap);
}