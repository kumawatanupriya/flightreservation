package com.anupriya.flightreservation.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReservationRequest {

    private Long id;
    private String passengerFirstName;
    private String passengerLastName;
    private String email;
    private String passengerPhone;
    private String nameOnTheCard;
    private String cardNumber;
    private String expirationDate;
    private String securityCode;
}
