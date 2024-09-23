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
@Table(name = "Airlines")
@Getter
@Setter
public class Airline {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long airlineId;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, unique = true, length = 10)
  private String code;

}