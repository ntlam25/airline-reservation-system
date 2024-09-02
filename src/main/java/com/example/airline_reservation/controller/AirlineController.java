package com.example.airline_reservation.controller;

import com.example.airline_reservation.dto.request.AirlineRequest;
import com.example.airline_reservation.dto.response.AirlineResponse;
import com.example.airline_reservation.service.Airline.AirlineService;
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
@RequestMapping("/api/airlines")
public class AirlineController {

  private final AirlineService service;

  public AirlineController(AirlineService service) {
    this.service = service;
  }

  @PostMapping("/add")
  public ResponseEntity<AirlineResponse> create(@RequestBody AirlineRequest request){
    return new  ResponseEntity<>(service.create(request),HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public AirlineResponse findById(@PathVariable Long id){
    return service.findById(id);
  }
  @PutMapping("/{id}")
  public AirlineResponse updateById(@PathVariable Long id,@RequestBody AirlineRequest airlineRequest){
    return service.update(id,airlineRequest);
  }

  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id){
    service.deleteById(id);
  }
}
