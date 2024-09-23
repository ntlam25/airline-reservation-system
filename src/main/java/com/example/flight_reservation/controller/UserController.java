package com.example.flight_reservation.controller;

import com.example.flight_reservation.dto.request.UserRequest;
import com.example.flight_reservation.dto.response.UserResponse;
import com.example.flight_reservation.service.User.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id){
        return service.findById(id);
    }
    @PutMapping("/{id}")
    public UserResponse updateById(@PathVariable Long id, @RequestBody UserRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
