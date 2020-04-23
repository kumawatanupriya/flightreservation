package com.anupriya.flightreservation.Controllers;

import com.anupriya.flightreservation.dto.ReservationRequest;
import com.anupriya.flightreservation.dto.ReservationUpdateRequest;
import com.anupriya.flightreservation.entities.Reservation;
import com.anupriya.flightreservation.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/confirm")
    public ResponseEntity<Reservation> confirmFlight(@RequestBody ReservationRequest request) {
        Reservation reservation = reservationService.bookFlight(request);
        LOGGER.info("confirm flight request " + request);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable Long id) {
        Reservation reservationDetails = reservationService.getReservationDetails(id);
        LOGGER.info("get reservation details " + reservationDetails);
        return reservationDetails;
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> updateReservation(@RequestBody ReservationUpdateRequest request) {
        Reservation reservation = reservationService.updateReservation(request);
        LOGGER.info("Update the flight reservation request" + request);
        return new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
    }
}
