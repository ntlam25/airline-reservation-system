package com.example.flight_reservation.controller;

import com.example.flight_reservation.dto.request.BookingRequest;
import com.example.flight_reservation.dto.request.SearchRequest;
import com.example.flight_reservation.dto.response.BookingResponse;
import com.example.flight_reservation.dto.response.VNPayResponse;
import com.example.flight_reservation.entity.Booking;
import com.example.flight_reservation.repository.BookingRepository;
import com.example.flight_reservation.service.Booking.IBookingService;
import com.example.flight_reservation.service.Filter.FilterService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

  private final IBookingService service;
  private final FilterService<Booking> filterService;
  private final BookingRepository repository;

  public BookingController(IBookingService service, FilterService<Booking> filterService, BookingRepository repository) {
    this.service = service;
    this.filterService = filterService;
    this.repository = repository;
  }


  @PostMapping("/add")
  public ResponseEntity<VNPayResponse> create(@RequestBody BookingRequest bookingRequest) {
    BookingResponse response = service.create(bookingRequest);
    VNPayResponse vnPayResponse = service.createPaymentUrl(response.getBookingId());
    return new ResponseEntity<>(vnPayResponse, HttpStatus.CREATED);
  }

  @GetMapping("/{bookingId}")
  public ResponseEntity<BookingResponse> findById(@PathVariable Long bookingId) {
    BookingResponse response = service.findById(bookingId);
    return ResponseEntity.ok(response);
  }
  @GetMapping("/all")
  public List<BookingResponse> getBookings(){
    return service.findAll();
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<BookingResponse>> findByUserId(@PathVariable Long userId){
    List<BookingResponse> responses = service.findBookingsByUserId(userId);
    return ResponseEntity.ok(responses);
  }
  @PostMapping("/filter")
  public List<Booking> findFlightBySpecification(@RequestBody SearchRequest request){
    Specification<Booking> responseSpecification = filterService
            .getSearchSpecification(request.getSearchDetailRequest(),request.getGlobalOperator());
    return repository.findAll(responseSpecification);
  }
}
