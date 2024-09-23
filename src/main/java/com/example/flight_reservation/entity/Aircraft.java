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
@Table(name = "AIRCRAFT")
@Getter
@Setter
public class Aircraft {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long aircraftId;

  @Column(nullable = false, length = 50)
  private String model;

  @Column(nullable = false)
  private Integer totalSeats;
}
