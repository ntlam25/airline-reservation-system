package com.example.airline_reservation.service.Airline;

import com.example.airline_reservation.dto.request.AirlineRequest;
import com.example.airline_reservation.dto.response.AirlineResponse;
import com.example.airline_reservation.entity.Airline;
import com.example.airline_reservation.service.BaseCRUDService;

public interface AirlineService extends
    BaseCRUDService<AirlineRequest, AirlineResponse, Airline,Long> {

}
