package com.example.flight_reservation.entity;

import com.example.flight_reservation.entity.Enums.RefundStatus;
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
@Table(name = "REFUNDS")
@Getter
@Setter
public class Refund {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long refundId;

  @ManyToOne
  @JoinColumn(name = "booking_id")
  private Booking booking;

  @Column(nullable = false,columnDefinition = "DOUBLE")
  private Double amount;

  @Column(nullable = false)
  private LocalDateTime refundDate;

  @Enumerated(EnumType.STRING)
  private RefundStatus status = RefundStatus.PENDING;

  @Column(columnDefinition = "TEXT")
  private String reason;
}