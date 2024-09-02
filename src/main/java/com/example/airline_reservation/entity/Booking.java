package com.example.airline_reservation.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
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
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "BookingDate",nullable = false)
  private LocalDateTime bookingDateTime;

  @Column(name = "Status")
  private String status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "passenger_id",nullable = false)
  private Passenger passenger;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flight_id",nullable = false)
  private Flight flight;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "seat_id",nullable = false,unique = true)
  private Seat seat;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private Account account;

  @OneToMany(mappedBy = "booking")
  private List<Luggage> luggage;

  @OneToMany(mappedBy = "booking")
  private List<Payment> payments;
}
