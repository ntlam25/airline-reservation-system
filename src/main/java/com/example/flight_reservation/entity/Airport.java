package com.example.flight_reservation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AIRPORTS")
@Getter
@Setter
public class Airport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long airportId;

  @Column(unique = true, nullable = false, length = 3)
  private String airportCode;

  @Column(nullable = false, length = 100)
  private String airportName;

  @Column(length = 50)
  private String city;

  @Column(length = 50)
  private String country;

  @Column(length = 50)
  private String timezone;

  // Getters and setters
}