package com.example.flight_reservation.controller;

import com.example.flight_reservation.dto.request.UserRequest;
import com.example.flight_reservation.dto.response.AuthResponse;
import com.example.flight_reservation.dto.response.UserResponse;
import com.example.flight_reservation.entity.User;
import com.example.flight_reservation.repository.UserRepository;
import com.example.flight_reservation.service.User.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final IUserService service;
    private final UserRepository userRepository;

    public AuthController(IUserService service,
                          UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest user) {
        return new ResponseEntity<>(service.create(user),HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserRequest user) {
        String token = service.verify(user);
        User userRes = userRepository.findUserByUsername(user.getUsername());
        return new ResponseEntity<>(new AuthResponse(token,userRes.getUserId(),userRes.getRole()),HttpStatus.OK);
    }
}
