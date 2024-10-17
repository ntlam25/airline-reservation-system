package com.example.flight_reservation.controller;

import com.example.flight_reservation.dto.request.UserRequest;
import com.example.flight_reservation.dto.response.UserResponse;
import com.example.flight_reservation.service.User.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final IUserService service;

    public AuthController(IUserService service) {
        this.service = service;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest user) {
        return new ResponseEntity<>(service.create(user),HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest user) {
        return service.verify(user);
    }
}
