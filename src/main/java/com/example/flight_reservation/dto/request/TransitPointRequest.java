package com.example.flight_reservation.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransitPointRequest {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer stopOrder;
    private Long airportId;
    private Long flightId;
}
