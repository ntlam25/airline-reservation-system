package com.example.flight_reservation.service.Flight;

import com.example.flight_reservation.dto.request.FlightRequest;
import com.example.flight_reservation.dto.request.TransitPointRequest;
import com.example.flight_reservation.dto.response.FlightResponse;
import com.example.flight_reservation.entity.*;
import com.example.flight_reservation.entity.Enums.FlightStatus;
import com.example.flight_reservation.exception.ResourceNotFoundException;
import com.example.flight_reservation.repository.AircraftRepository;
import com.example.flight_reservation.repository.AirlineRepository;
import com.example.flight_reservation.repository.AirportRepository;
import com.example.flight_reservation.repository.FlightRepository;
import com.example.flight_reservation.service.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService extends AbstractCrudService<FlightRequest, FlightResponse, FlightRepository, Flight,Long> implements IFlightService {
    private final AircraftRepository aircraftRepository;
    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;

    protected FlightService(FlightRepository repository,
                            AircraftRepository aircraftRepository,
                            AirlineRepository airlineRepository,
                            AirportRepository airportRepository) {
        super(repository);
        this.aircraftRepository = aircraftRepository;
        this.airlineRepository = airlineRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    protected Flight createAndSave(FlightRequest request) {
        Aircraft aircraft = aircraftRepository.findById(request.getAircraftId())
                .orElseThrow(() -> new ResourceNotFoundException("Aircraft not found"));
        Airline airline = airlineRepository.findById(request.getAirlineId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Airport arrivalAirport = airportRepository.findById(request.getArrivalAirportId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Airport departureAirport = airportRepository.findById(request.getDepartureAirportId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Flight flight = new Flight();
        createFlight(request, flight, aircraft, airline, arrivalAirport, departureAirport);

        flight.setStatus(FlightStatus.SCHEDULED);
        return repository.save(flight);
    }

    @Override
    protected Flight updateAndSave(Long id, FlightRequest request) {
        Flight flight = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not found Flight with id "+id));
        Aircraft aircraft = aircraftRepository.findById(request.getAircraftId())
                .orElseThrow(() -> new ResourceNotFoundException("Aircraft not found"));
        Airline airline = airlineRepository.findById(request.getAirlineId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Airport arrivalAirport = airportRepository.findById(request.getArrivalAirportId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Airport departureAirport = airportRepository.findById(request.getDepartureAirportId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        createFlight(request, flight, aircraft, airline, arrivalAirport, departureAirport);
        return repository.save(flight);
    }

    private void createFlight(FlightRequest request, Flight flight, Aircraft aircraft, Airline airline, Airport arrivalAirport, Airport departureAirport) {
        flight.setAircraft(aircraft);
        flight.setAirline(airline);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);
        flight.setFlightNumber(request.getFlightNumber());
        flight.setArrivalTime(request.getArrivalTime());
        flight.setDepartureTime(request.getDepartureTime());
        flight.setBasePrice(request.getBasePrice());
        for (TransitPointRequest transitPointRequest : request.getTransitPointList()) {

            Airport transitAirport = airportRepository.findById(transitPointRequest.getAirportId())
                    .orElseThrow(() -> new ResourceNotFoundException("Transit airport not found"));

            TransitPoint transitPoint = new TransitPoint();
            transitPoint.setFlight(flight);
            transitPoint.setAirport(transitAirport);
            transitPoint.setArrivalTime(transitPointRequest.getArrivalTime());
            transitPoint.setDepartureTime(transitPointRequest.getDepartureTime());
            transitPoint.setStopOrder(transitPointRequest.getStopOrder());

            flight.addTransitPoint(transitPoint);
        }
    }

    @Override
    protected FlightResponse toResponse(Flight domainEntity) {
        FlightResponse response = new FlightResponse();
        response.setFlightId(domainEntity.getFlightId());
        response.setArrivalTime(domainEntity.getArrivalTime());
        response.setDepartureTime(domainEntity.getDepartureTime());
        response.setArrivalAirport(domainEntity.getArrivalAirport());
        response.setDepartureAirport(domainEntity.getDepartureAirport());
        response.setStatus(domainEntity.getStatus());
        response.setFlightNumber(domainEntity.getFlightNumber());
        response.setBasePrice(domainEntity.getBasePrice());
        response.setAircraft(domainEntity.getAircraft());
        response.setAirline(domainEntity.getAirline());
        response.setTransitPointList(domainEntity.getTransitPoints());
        return response;
    }

    @Override
    public List<FlightResponse> searchFlights(String depAirport, String arrAirport, String depDate) {
        LocalDate departureDate = LocalDate.parse(depDate);
        List<Flight> flights = repository.findFlights(depAirport, arrAirport, departureDate);
        List<FlightResponse> results = new ArrayList<>();
        for( Flight f: flights){
            results.add(toResponse(f));
        }
        return results;
    }

    @Override
    public List<FlightResponse> findAll() {
        List<FlightResponse> responses = new ArrayList<>();
        for(Flight flight : repository.findAll()){
            responses.add(toResponse(flight));
        }
        return responses;
    }
}
