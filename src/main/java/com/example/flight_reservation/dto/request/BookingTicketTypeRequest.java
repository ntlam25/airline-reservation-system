package com.example.flight_reservation.dto.request;

import lombok.Data;

@Data
public class BookingTicketTypeRequest {
  private Long ticketTypeId;
  private Integer quantity;
}
