package com.anupriya.flightreservation.Controllers;

import com.anupriya.flightreservation.entities.User;
import com.anupriya.flightreservation.repos.UserRepository;
import com.anupriya.flightreservation.services.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        LOGGER.info("in registerUser() method " + user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<String>("successfully registered", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, String password){
        boolean loginResponse = securityService.login(email, password);
        if(loginResponse){
            return new ResponseEntity<String>("successfully Logged In", HttpStatus.OK);
        }
        LOGGER.info("Inside Login method for email " + email);
        return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
    }
}
