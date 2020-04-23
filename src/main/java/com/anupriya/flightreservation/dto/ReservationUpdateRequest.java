package com.anupriya.flightreservation.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReservationUpdateRequest {

    private Long id;
    private Boolean checkedIn;
    private int numberOfBags;
}
