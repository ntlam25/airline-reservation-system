package com.example.airline_reservation.service.Flight;

import com.example.airline_reservation.dto.request.FlightRequest;
import com.example.airline_reservation.dto.response.FlightResponse;
import com.example.airline_reservation.entity.Flight;
import com.example.airline_reservation.service.BaseCRUDService;

public interface FlightService extends
    BaseCRUDService<FlightRequest, FlightResponse, Flight,Long> {

}
