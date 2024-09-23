package com.example.flight_reservation.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PassengerResponse {
  private Long passengerId;
  private String firstName;
  private String lastName;
  private String passportNumber;
  private LocalDate dateOfBirth;
}
