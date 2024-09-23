package com.example.flight_reservation.entity;

import com.example.flight_reservation.entity.Enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BOOKINGS")
@Getter
@Setter
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookingId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "flight_id")
  private Flight flight;

  @Column(nullable = false)
  private LocalDateTime bookingDate;

  @Enumerated(EnumType.STRING)
  private BookingStatus status = BookingStatus.PENDING;

  @Column(nullable = false)
  private Long totalPrice;

  @JsonManagedReference
  @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
  private List<Passenger> passengers = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
  private List<Payment> payments = new ArrayList<>();
  @JsonManagedReference
  @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
  private List<BookingTicketType> bookingTicketTypes = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
  private List<Luggage> luggage = new ArrayList<>();

  public void addPassenger(Passenger passenger) {
    passengers.add(passenger);
    passenger.setBooking(this);
  }
  public void addBookingTicketType(BookingTicketType bookingTicketType) {
    bookingTicketTypes.add(bookingTicketType);
    bookingTicketType.setBooking(this);
  }

  public void addLuggage(Luggage luggage1) {
    luggage.add(luggage1);
    luggage1.setBooking(this);
  }
}