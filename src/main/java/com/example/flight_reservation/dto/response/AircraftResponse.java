package com.example.flight_reservation.dto.response;

import lombok.Data;

@Data
public class AircraftResponse {
  private Long id;
  private String model;
  private Integer totalSeat;
}
