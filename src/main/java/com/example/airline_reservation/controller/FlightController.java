package com.example.airline_reservation.controller;

import com.example.airline_reservation.dto.request.FlightRequest;
import com.example.airline_reservation.dto.response.FlightResponse;
import com.example.airline_reservation.service.Flight.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
  private final FlightService service;

  public FlightController(FlightService service) {
    this.service = service;
  }

  @PostMapping("/add")
  public ResponseEntity<FlightResponse> create(@RequestBody FlightRequest request){
    return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
  }
  @GetMapping("/{id}")
  public FlightResponse findById(@PathVariable Long id){
    return service.findById(id);
  }
  @PutMapping("/{id}")
  public FlightResponse update(@PathVariable Long id, @RequestBody FlightRequest request){
    return service.update(id,request);
  }
  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id){
    service.deleteById(id);
  }
}
