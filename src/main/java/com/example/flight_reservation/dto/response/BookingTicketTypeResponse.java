package com.example.flight_reservation.dto.response;

import lombok.Data;

@Data
public class BookingTicketTypeResponse {
    private Long ticketTypeId;
    private Long bookingId;
    private Integer quantity;
}
