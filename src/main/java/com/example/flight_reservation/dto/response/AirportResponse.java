package com.example.flight_reservation.dto.response;

import lombok.Data;

@Data
public class AirportResponse {
  private Long id;
  private String code;
  private String name;
  private String city;
  private String country;
}
