package com.example.flight_reservation.dto.request;

import lombok.Data;

@Data
public class AircraftRequest {
  private String model;
  private Integer totalSeat;
}
