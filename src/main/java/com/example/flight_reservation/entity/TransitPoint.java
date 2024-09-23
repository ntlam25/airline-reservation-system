package com.example.flight_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TRANSIT_POINTS")
@Getter
@Setter
public class TransitPoint {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transitId;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flight_id", nullable = false)
  private Flight flight;

  @ManyToOne
  @JoinColumn(name = "airport_id", nullable = false)
  private Airport airport;

  @Column(nullable = false)
  private LocalDateTime arrivalTime;

  @Column(nullable = false)
  private LocalDateTime departureTime;

  @Column(nullable = false)
  private Integer stopOrder;


}
