package com.example.flight_reservation.service.TransitPoint;

import com.example.flight_reservation.dto.request.AirlineRequest;
import com.example.flight_reservation.dto.request.TransitPointRequest;
import com.example.flight_reservation.dto.response.AirlineResponse;
import com.example.flight_reservation.dto.response.TransitPointResponse;

import com.example.flight_reservation.entity.Airline;
import com.example.flight_reservation.entity.Airport;
import com.example.flight_reservation.entity.Flight;
import com.example.flight_reservation.entity.TransitPoint;


import com.example.flight_reservation.exception.ResourceNotFoundException;
import com.example.flight_reservation.repository.AirlineRepository;
import com.example.flight_reservation.repository.AirportRepository;
import com.example.flight_reservation.repository.FlightRepository;
import com.example.flight_reservation.repository.TransitPointRepository;
import com.example.flight_reservation.service.AbstractCrudService;

import com.example.flight_reservation.service.Airline.IAirlineService;
import org.springframework.stereotype.Service;

@Service
public class TransitPointService extends AbstractCrudService<TransitPointRequest, TransitPointResponse, TransitPointRepository, TransitPoint,Long> implements ITransitPointService {

    private final AirportRepository airportRepository;
    private  final FlightRepository flightRepository;


    protected TransitPointService(TransitPointRepository repository, AirportRepository airportRepository, FlightRepository flightRepository) {
        super(repository);
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    protected TransitPoint createAndSave(TransitPointRequest request) {

        Airport airport = airportRepository.findById(request.getAirportId())
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found"));
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        TransitPoint transitPoint = new TransitPoint();
        transitPoint.setFlight(flight);
        transitPoint.setAirport(airport);
        transitPoint.setArrivalTime(request.getArrivalTime());
        transitPoint.setDepartureTime(request.getDepartureTime());
        transitPoint.setStopOrder(request.getStopOrder());
        return repository.save(transitPoint);
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
