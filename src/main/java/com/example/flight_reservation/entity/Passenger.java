package com.example.flight_reservation.entity;

import com.example.flight_reservation.entity.Enums.SeatClass;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PASSENGERS")
@Getter
@Setter
public class Passenger {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long passengerId;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "booking_id")
  private Booking booking;

  @Column(nullable = false, length = 50)
  private String firstName;

  @Column(nullable = false, length = 50)
  private String lastName;

  @Column(nullable = false, length = 20)
  private String passportNumber;

  @Column(nullable = false)
  private LocalDate dateOfBirth;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SeatClass seatClass;
}