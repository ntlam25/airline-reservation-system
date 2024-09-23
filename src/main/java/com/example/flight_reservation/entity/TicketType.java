package com.example.flight_reservation.entity;

import com.example.flight_reservation.entity.Enums.SeatClass;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TICKET_TYPES")
@Getter
@Setter
public class TicketType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ticketTypeId;

  @Column(nullable = false, length = 50)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SeatClass seatClass;

  @Column(nullable = false)
  private Long priceMultiplier;

}