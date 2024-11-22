package com.example.flight_reservation.controller;

import com.example.flight_reservation.dto.request.AircraftRequest;
import com.example.flight_reservation.dto.response.AircraftResponse;
import com.example.flight_reservation.dto.response.FlightResponse;
import com.example.flight_reservation.service.Aircraft.IAircraftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {
    private final IAircraftService service;

    public AircraftController(IAircraftService service) {
        this.service = service;
    }


    @PostMapping("/add")
    public ResponseEntity<AircraftResponse> create(@RequestBody AircraftRequest request){
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public AircraftResponse findById(@PathVariable Long id){
        return service.findById(id);
    }
    @GetMapping
    public List<AircraftResponse> getAircrafts(){
        return service.findAll();
    }
    @PutMapping("/{id}")
    public AircraftResponse update(@PathVariable Long id, @RequestBody AircraftRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
