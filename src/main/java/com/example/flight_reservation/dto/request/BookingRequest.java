package com.example.flight_reservation.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class BookingRequest {
  private Long userId;
  private Long flightId;
  private List<PassengerRequest> passengers;
  private List<LuggageRequest> luggage;
  private List<BookingTicketTypeRequest> bookingTicketTypes;
}
