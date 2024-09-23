package com.example.flight_reservation.service.Airport;

import com.example.flight_reservation.dto.request.AirportRequest;
import com.example.flight_reservation.dto.response.AirportResponse;
import com.example.flight_reservation.entity.Airport;
import com.example.flight_reservation.service.BaseCRUDService;

public interface IAirportService extends BaseCRUDService<AirportRequest, AirportResponse, Airport,Long> {

}