package com.example.flight_reservation.service.TransitPoint;

import com.example.flight_reservation.dto.request.AirlineRequest;
import com.example.flight_reservation.dto.request.TransitPointRequest;
import com.example.flight_reservation.dto.response.AirlineResponse;
import com.example.flight_reservation.dto.response.TransitPointResponse;

import com.example.flight_reservation.entity.Airline;
import com.example.flight_reservation.entity.TransitPoint;


import com.example.flight_reservation.repository.AirlineRepository;
import com.example.flight_reservation.repository.TransitPointRepository;
import com.example.flight_reservation.service.AbstractCrudService;

import com.example.flight_reservation.service.Airline.IAirlineService;
import org.springframework.stereotype.Service;

@Service
public class TransitPointService extends AbstractCrudService<TransitPointRequest, TransitPointResponse, TransitPointRepository, TransitPoint,Long> implements ITransitPointService {

    protected TransitPointService(TransitPointRepository repository) {
        super(repository);
    }

    @Override
    protected TransitPoint createAndSave(TransitPointRequest request) {
        return null;
    }

    @Override
    protected TransitPoint updateAndSave(Long id, TransitPointRequest request) {
        return null;
    }

    @Override
    protected TransitPointResponse toResponse(TransitPoint domainEntity) {
        return null;
    }


}
