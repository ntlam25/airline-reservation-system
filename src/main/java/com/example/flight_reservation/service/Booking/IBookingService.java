package com.example.flight_reservation.service.Booking;

import com.example.flight_reservation.dto.request.BookingRequest;
import com.example.flight_reservation.dto.response.BookingResponse;
import com.example.flight_reservation.dto.response.VNPayResponse;
import com.example.flight_reservation.entity.Booking;
import com.example.flight_reservation.service.BaseCRUDService;

import java.util.List;

public interface IBookingService extends BaseCRUDService<BookingRequest, BookingResponse, Booking,Long> {
    VNPayResponse createPaymentUrl(Long id);
    List<BookingResponse> findBookingsByUserId(Long userId);
}
