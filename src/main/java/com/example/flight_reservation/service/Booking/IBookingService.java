package com.example.flight_reservation.service.Booking;

import com.example.flight_reservation.dto.request.BookingRequest;
import com.example.flight_reservation.dto.response.BookingResponse;
import com.example.flight_reservation.dto.response.VNPayResponse;
import com.example.flight_reservation.entity.Booking;
import com.example.flight_reservation.service.BaseCRUDService;

public interface IBookingService extends BaseCRUDService<BookingRequest, BookingResponse, Booking,Long> {
    VNPayResponse createPaymentUrl(BookingResponse response);
}
