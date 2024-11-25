package com.example.flight_reservation.controller;

import com.example.flight_reservation.dto.request.AirportRequest;
import com.example.flight_reservation.dto.response.AirportResponse;
import com.example.flight_reservation.dto.response.FlightResponse;
import com.example.flight_reservation.service.Airport.IAirportService;
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

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
  private final IAirportService service;

  public AirportController(IAirportService service) {
    this.service = service;
  }


  @PostMapping("/add")
  public ResponseEntity<AirportResponse> create(@RequestBody AirportRequest request){
    return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
  }
  @GetMapping("/{id}")
  public AirportResponse findById(@PathVariable Long id){
    return service.findById(id);
  }
  @GetMapping("/all")
  public List<AirportResponse> getAirports(){
    return service.findAll();
  }
  @PutMapping("/{id}")
  public AirportResponse update(@PathVariable Long id, @RequestBody AirportRequest request){
    return service.update(id,request);
  }
  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id){
    service.deleteById(id);
  }
}
