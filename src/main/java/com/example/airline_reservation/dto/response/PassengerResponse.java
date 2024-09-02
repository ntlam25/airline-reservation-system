package com.example.airline_reservation.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PassengerResponse {
  private Long id;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private String phoneNumber;
  private String email;
  private String nationality;
  private String passportNumber;
}
