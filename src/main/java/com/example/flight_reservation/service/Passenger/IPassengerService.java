package com.example.flight_reservation.service.Passenger;

import com.example.flight_reservation.dto.request.PassengerRequest;
import com.example.flight_reservation.dto.response.PassengerResponse;
import com.example.flight_reservation.entity.Passenger;
import com.example.flight_reservation.service.BaseCRUDService;

public interface IPassengerService extends BaseCRUDService<PassengerRequest, PassengerResponse, Passenger,Long> {

}
