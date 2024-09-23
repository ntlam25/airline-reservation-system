package com.example.flight_reservation.service.Aircraft;

import com.example.flight_reservation.dto.request.AircraftRequest;
import com.example.flight_reservation.dto.response.AircraftResponse;
import com.example.flight_reservation.entity.Aircraft;
import com.example.flight_reservation.service.BaseCRUDService;

public interface IAircraftService extends BaseCRUDService<AircraftRequest, AircraftResponse, Aircraft,Long> {

}
