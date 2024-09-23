package com.example.flight_reservation.dto.response;

import lombok.Data;

@Data
public class LuggageResponse {
    private Long id;
    private Double price;
    private Double weight;
    private Long bookingId;
}
