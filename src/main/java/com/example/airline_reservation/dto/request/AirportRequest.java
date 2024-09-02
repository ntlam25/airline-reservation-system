package com.example.airline_reservation.dto.request;

import lombok.Data;

@Data
public class AirportRequest {
  private String name;
  private String city;
  private String country;
  private String code;
}
