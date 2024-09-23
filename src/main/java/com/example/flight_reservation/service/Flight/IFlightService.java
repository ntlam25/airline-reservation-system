package com.example.flight_reservation.service.Flight;

import com.example.flight_reservation.dto.request.FlightRequest;
import com.example.flight_reservation.dto.response.FlightResponse;
import com.example.flight_reservation.entity.Flight;
import com.example.flight_reservation.service.BaseCRUDService;

public interface IFlightService extends BaseCRUDService<FlightRequest, FlightResponse, Flight,Long> {
}
