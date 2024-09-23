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
@Table(name = "BOOKING_TICKET_TYPES")
@Getter
@Setter
public class BookingTicketType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookingTicketTypeId;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "booking_id")
  private Booking booking;

  @ManyToOne
  @JoinColumn(name = "ticket_type_id")
  private TicketType ticketType;

  @Column(nullable = false)
  private Integer quantity;
}
