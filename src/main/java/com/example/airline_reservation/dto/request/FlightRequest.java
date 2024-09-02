package com.example.airline_reservation.dto.request;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class FlightRequest {
  private String flightNumber;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private String aircraftType;
  private Long airlinesId;
  private Long departureAirportId;
  private Long arrivalAirportId;
}
