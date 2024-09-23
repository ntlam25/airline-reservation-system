package com.example.flight_reservation.dto.request;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FlightRequest {
    private String flightNumber;
    private Long aircraftId;
    private Long airlineId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Long departureAirportId;
    private Long arrivalAirportId;
    private Long basePrice;
    private List<TransitPointRequest> transitPointList;
}
