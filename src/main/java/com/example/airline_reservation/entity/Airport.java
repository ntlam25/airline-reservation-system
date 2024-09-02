package com.example.airline_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Airport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "Name",nullable = false)
  private String name;
  @Column(name = "City",nullable = false)
  private String city;
  @Column(name = "Country",nullable = false)
  private String country;
  @Column(name = "Code",nullable = false)
  private String code;

  @JsonBackReference
  @OneToMany(mappedBy = "departureAirport")
  private List<Flight> departureFlights;

  @JsonBackReference
  @OneToMany(mappedBy = "arrivalAirport")
  private List<Flight> arrivalFlights;
}
