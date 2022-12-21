package com.emsi.pfe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    private String publicId;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String phoneNumber;
    private String cin;
    private Date birthday;
}
