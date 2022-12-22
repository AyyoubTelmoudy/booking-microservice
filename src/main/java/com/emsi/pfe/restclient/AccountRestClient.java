package com.emsi.pfe.restclient;

import com.emsi.pfe.dto.PassengerDTO;

public interface AccountRestClient {
    PassengerDTO getPassengerByPublicId(String passengerPublicId);
}
