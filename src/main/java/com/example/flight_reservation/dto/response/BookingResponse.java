package com.example.flight_reservation.dto.response;

import com.example.flight_reservation.entity.BookingTicketType;
import com.example.flight_reservation.entity.Enums.BookingStatus;
import com.example.flight_reservation.entity.Luggage;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class BookingResponse {
  private Long bookingId;
  private UserResponse user;
  private Long flightId;
  private BookingStatus status;
  private Long totalPrice;
  private LocalDateTime bookingDate;
  private List<PassengerResponse> passengers;
  private List<Luggage> luggage;
  private List<BookingTicketType> bookingTicketType;
}
