package com.anupriya.flightreservation.services;

import com.anupriya.flightreservation.dto.ReservationRequest;
import com.anupriya.flightreservation.dto.ReservationUpdateRequest;
import com.anupriya.flightreservation.entities.Flight;
import com.anupriya.flightreservation.entities.Passenger;
import com.anupriya.flightreservation.entities.Reservation;
import com.anupriya.flightreservation.repos.FlightRepository;
import com.anupriya.flightreservation.repos.PassengerRepository;
import com.anupriya.flightreservation.repos.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservationService {

    private static final Logger LOGGGER = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional
    public Reservation bookFlight(ReservationRequest request) {

        //Make payment

        Long flightId = request.getId();
        Optional<Flight> flight = flightRepository.findById(flightId);
        Passenger passenger = Passenger.builder()
                .firstName(request.getPassengerFirstName())
                .lastName(request.getPassengerLastName())
                .email(request.getEmail())
                .phone(request.getPassengerPhone())
                .build();
        Passenger savedPassenger = passengerRepository.save(passenger);
        LOGGGER.info("save passenger details in db " + passenger);


        flight.map(f -> {
            Reservation savedReservation = Reservation.builder()
                    .flight(f)
                    .passenger(savedPassenger)
                    .checkedIn(false)
                    .build();
            Reservation reservation = reservationRepository.save(savedReservation);
            LOGGGER.info("save passanger reservation in db " + reservation);
            return reservation;

        });
        return null;
    }

    public Reservation getReservationDetails(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.orElseThrow(NoSuchElementException::new);
    }

    public Reservation updateReservation(ReservationUpdateRequest request) {
        Optional<Reservation> reservation = reservationRepository.findById(request.getId());
        reservation.map(res -> {
            res.setCheckedIn(request.getCheckedIn());
            res.setNumberOfBags(request.getNumberOfBags());
            LOGGGER.info("updating reservation details for a reservation " + reservation);
            return reservationRepository.save(res);

        }).orElseThrow(NoSuchElementException::new);
        return null;
    }
}
