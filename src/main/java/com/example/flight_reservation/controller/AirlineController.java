package com.example.flight_reservation.controller;

import com.example.flight_reservation.dto.request.AirlineRequest;
import com.example.flight_reservation.dto.response.AirlineResponse;
import com.example.flight_reservation.dto.response.FlightResponse;
import com.example.flight_reservation.service.Airline.IAirlineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {
    private final IAirlineService service;

    public AirlineController(IAirlineService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<AirlineResponse> create(@RequestBody AirlineRequest request){
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public AirlineResponse findById(@PathVariable Long id){
        return service.findById(id);
    }
    @GetMapping("/all")
    public List<AirlineResponse> getAirline(){
        return service.findAll();
    }
    @PutMapping("/{id}")
    public AirlineResponse update(@PathVariable Long id, @RequestBody AirlineRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
