package com.example.airline_reservation.dto.response;

import lombok.Data;

@Data
public class AirportResponse {
  private Long id;
  private String name;
  private String city;
  private String country;
  private String code;
}
