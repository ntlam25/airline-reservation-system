package com.example.flight_reservation.service.Airline;

import com.example.flight_reservation.dto.request.AirlineRequest;
import com.example.flight_reservation.dto.response.AirlineResponse;
import com.example.flight_reservation.entity.Airline;
import com.example.flight_reservation.service.BaseCRUDService;

public interface IAirlineService extends BaseCRUDService<AirlineRequest, AirlineResponse, Airline,Long> {
}
