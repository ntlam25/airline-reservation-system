package com.example.airline_reservation.service.Airport;

import com.example.airline_reservation.dto.request.AirportRequest;
import com.example.airline_reservation.dto.response.AirportResponse;
import com.example.airline_reservation.entity.Airport;
import com.example.airline_reservation.service.BaseCRUDService;

public interface AirportService extends BaseCRUDService<AirportRequest, AirportResponse, Airport,Long> {

}
