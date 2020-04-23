package com.anupriya.flightreservation.Controllers;

import com.anupriya.flightreservation.entities.Flight;
import com.anupriya.flightreservation.repos.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class FlightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private  FlightRepository flightRepository;

    @PostMapping("/find")
    public List<Flight> findFlight(@RequestParam String from, String to, String departureDate) throws ParseException {
        Date dateOfDeparture=new SimpleDateFormat("yyyy-MM-dd").parse(departureDate);
        LOGGER.info("find flight from " + from + "to " + to);
        List<Flight> flights = flightRepository.findFlights(from, to, dateOfDeparture);
        return flights;
    }

    @PostMapping("admin/flights")
    public String addFlight(){
        return "accessed";
    }
}
