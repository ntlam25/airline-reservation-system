package com.example.airline_reservation.dto.response;

import com.example.airline_reservation.entity.Account;
import com.example.airline_reservation.entity.Flight;
import com.example.airline_reservation.entity.Passenger;
import com.example.airline_reservation.entity.Seat;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BookingResponse {
  private Long id;
  private LocalDateTime bookingDate;
  private String status;
  private Passenger passenger;
  private Flight flight;
  private Seat seat;
  private Account account;
}
