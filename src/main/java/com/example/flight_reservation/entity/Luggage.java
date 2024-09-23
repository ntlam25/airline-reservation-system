package com.example.flight_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Luggage")
@Getter
@Setter
public class Luggage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long luggageId;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "booking_id", nullable = false)
  private Booking booking;

  @Column(nullable = false,columnDefinition = "DOUBLE")
  private Double weight;

  @Column(nullable = false,columnDefinition = "DOUBLE")
  private Double price;
}