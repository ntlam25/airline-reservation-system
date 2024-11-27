package com.example.flight_reservation.service.TransitPoint;


import com.example.flight_reservation.dto.request.TransitPointRequest;

import com.example.flight_reservation.dto.response.TransitPointResponse;

import com.example.flight_reservation.entity.TransitPoint;
import com.example.flight_reservation.service.BaseCRUDService;

public interface ITransitPointService extends BaseCRUDService<TransitPointRequest, TransitPointResponse, TransitPoint,Long> {
}
