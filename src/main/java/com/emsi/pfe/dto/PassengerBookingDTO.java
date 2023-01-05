package com.emsi.pfe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerBookingDTO {
    private String passengerPublicId;
    private String announcementPublicId;
    private  boolean confirmed;
    private Date date;

}
