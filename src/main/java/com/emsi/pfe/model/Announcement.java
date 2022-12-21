package com.emsi.pfe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    private String publicId;
    private String start;
    private String destination;
    private  long price;
    private Date date;
    private int nSeat;

}
