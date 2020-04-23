package com.anupriya.flightreservation.entities;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Passenger extends AbstractEntity{

    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;
}
