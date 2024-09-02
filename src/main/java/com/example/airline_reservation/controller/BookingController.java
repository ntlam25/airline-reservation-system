package com.example.airline_reservation.controller;

import com.example.airline_reservation.dto.request.BookingRequest;
import com.example.airline_reservation.dto.response.BookingResponse;
import com.example.airline_reservation.service.Booking.BookingService;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/booking")
public class BookingController {

  private final BookingService service;

  public BookingController(BookingService service) {
    this.service = service;
  }

  @PostMapping("/add")
  public BookingResponse create(@RequestBody BookingRequest request){
    return service.create(request);
  }
  @GetMapping("/{id}")
  public BookingResponse findById(@PathVariable Long id){
    return service.findById(id);
  }
  @PutMapping("/{id}")
  public BookingResponse update(@PathVariable Long id, @RequestBody BookingRequest request){
    return service.update(id,request);
  }
  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id){
    service.deleteById(id);
  }
}
