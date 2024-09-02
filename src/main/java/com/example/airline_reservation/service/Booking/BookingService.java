package com.example.airline_reservation.service.Booking;

import com.example.airline_reservation.dto.request.BookingRequest;
import com.example.airline_reservation.dto.response.BookingResponse;
import com.example.airline_reservation.entity.Booking;
import com.example.airline_reservation.service.BaseCRUDService;

public interface BookingService extends BaseCRUDService<BookingRequest, BookingResponse,Booking,Long> {

}
