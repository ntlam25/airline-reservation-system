package com.example.flight_reservation.dto.request;

import lombok.Data;

@Data
public class AirportRequest {
  private String code;
  private String name;
  private String city;
  private String country;
}
