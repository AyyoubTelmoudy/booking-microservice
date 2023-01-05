package com.emsi.pfe.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "passenger_booking")
@Data @AllArgsConstructor @NoArgsConstructor
public class PassengerBooking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String publicId;
    private String passengerPublicId;
    private String announcementPublicId;
    private boolean confirmed;
    private Date date;
}
