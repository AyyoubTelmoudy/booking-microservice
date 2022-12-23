package com.emsi.pfe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class PassengerDTO {
    private String publicId;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String phoneNumber;
    private String cin;
    private Date birthday;
}

