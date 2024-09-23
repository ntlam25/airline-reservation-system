package com.example.flight_reservation.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransitPointResponse {
    private Long id;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Long stopOrder;
    private AirportResponse airport;
    private FlightResponse flight;
}
