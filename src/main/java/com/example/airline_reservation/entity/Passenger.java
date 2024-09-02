package com.example.airline_reservation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "FirstName",nullable = false)
  private String firstName;
  @Column(name = "LastName",nullable = false)
  private String lastName;
  @Column(name = "DateOfBirth",nullable = false)
  private LocalDate dateOfBirth;
  @Column(name = "PassportNumber")
  private String passportNumber;
  @Column(name = "Nationality")
  private String nationality;
  @Column(name = "Email",nullable = false)
  private String email;
  @Column(name = "PhoneNumber")
  private String phoneNumber;

  @OneToMany(mappedBy = "passenger")
  private List<Booking> bookings;
}
