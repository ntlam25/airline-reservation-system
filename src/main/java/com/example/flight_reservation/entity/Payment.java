package com.example.flight_reservation.entity;

import com.example.flight_reservation.entity.Enums.PaymentMethod;
import com.example.flight_reservation.entity.Enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PAYMENTS")
@Getter
@Setter
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long paymentId;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "booking_id")
  private Booking booking;

  @Column(nullable = false,columnDefinition = "DOUBLE")
  private Double amount;

  @Column(nullable = false)
  private LocalDateTime paymentDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  private PaymentStatus status = PaymentStatus.PENDING;

  @Column(length = 100)
  private String transactionId;

  // Getters and setters
}
