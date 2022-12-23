package com.emsi.pfe.restclient.restclientimpl;

import com.emsi.pfe.dto.PassengerDTO;
import com.emsi.pfe.restclient.AccountRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AccountRestClientImpl implements AccountRestClient {
    @Autowired
    RestTemplate restTemplate;
    @Override
    public PassengerDTO getPassengerByPublicId(String passengerPublicId) {
        PassengerDTO passengerDTO= restTemplate.getForObject("https://accounts-microservice-production.up.railway.app/passengers/"+passengerPublicId, PassengerDTO.class);
        return passengerDTO;
    }
}
