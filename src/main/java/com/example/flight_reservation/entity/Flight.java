package com.example.flight_reservation.entity;

import com.example.flight_reservation.entity.Enums.FlightStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FLIGHTS")
@Getter
@Setter
public class Flight {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long flightId;

  @Column(nullable = false, length = 20)
  private String flightNumber;

  @ManyToOne
  @JoinColumn(name = "aircraft_id")
  private Aircraft aircraft;

  @ManyToOne
  @JoinColumn(name = "AirlineID")
  private Airline airline;

  @Column(nullable = false)
  private LocalDateTime departureTime;

  @Column(nullable = false)
  private LocalDateTime arrivalTime;

  @ManyToOne
  @JoinColumn(name = "departure_airport_id")
  private Airport departureAirport;

  @ManyToOne
  @JoinColumn(name = "arrival_airport_id")
  private Airport arrivalAirport;

  @Column(nullable = false,columnDefinition = "DOUBLE")
  private Long basePrice;

  @Enumerated(EnumType.STRING)
  private FlightStatus status = FlightStatus.SCHEDULED;

  @JsonManagedReference
  @OneToMany(mappedBy = "flight",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
  private List<TransitPoint> transitPoints = new ArrayList<>();

  public void addTransitPoint(TransitPoint transitPoint) {
    transitPoints.add(transitPoint);
    transitPoint.setFlight(this);
  }
}