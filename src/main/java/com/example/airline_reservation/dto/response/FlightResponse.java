package com.example.airline_reservation.dto.response;

import com.example.airline_reservation.entity.Airline;
import com.example.airline_reservation.entity.Airport;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class FlightResponse {
  private Long id;
  private String flightNumber;
  private LocalDateTime departureTime;
  private LocalDateTime arrivalTime;
  private String aircraftType;
  private Airline airline;
  private Airport departureAirport;
  private Airport arrivalAirport;
}
