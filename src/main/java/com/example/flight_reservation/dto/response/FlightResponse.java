package com.example.flight_reservation.dto.response;

import com.example.flight_reservation.entity.Aircraft;
import com.example.flight_reservation.entity.Airline;
import com.example.flight_reservation.entity.Airport;
import com.example.flight_reservation.entity.Enums.FlightStatus;
import com.example.flight_reservation.entity.TransitPoint;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FlightResponse {
    private Long flightId;
    private String flightNumber;
    private Aircraft aircraft;
    private Airline airline;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private Long basePrice;
    private FlightStatus status;
    private List<TransitPoint> transitPointList;
}
