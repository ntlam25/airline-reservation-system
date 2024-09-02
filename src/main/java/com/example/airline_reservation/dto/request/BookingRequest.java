package com.example.airline_reservation.dto.request;

import lombok.Data;

@Data
public class BookingRequest {
  private Long accountId;
  private Long flightId;
  private Long passengerId;
  private Long seatId;
  private String status;
}
