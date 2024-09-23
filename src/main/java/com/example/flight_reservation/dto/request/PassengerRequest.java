package com.example.flight_reservation.dto.request;

import java.time.LocalDate;

import com.example.flight_reservation.entity.Enums.SeatClass;
import lombok.Data;

@Data
public class PassengerRequest {
  private String firstName;
  private String lastName;
  private String passportNumber;
  private LocalDate dateOfBirth;
  private SeatClass seatClass;
}
