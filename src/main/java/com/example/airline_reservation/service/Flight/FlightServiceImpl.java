package com.example.airline_reservation.service.Flight;

import com.example.airline_reservation.dto.request.FlightRequest;
import com.example.airline_reservation.dto.response.FlightResponse;
import com.example.airline_reservation.entity.Airline;
import com.example.airline_reservation.entity.Airport;
import com.example.airline_reservation.entity.Flight;
import com.example.airline_reservation.exception.ResourceNotFoundException;
import com.example.airline_reservation.repository.AirlineRepository;
import com.example.airline_reservation.repository.AirportRepository;
import com.example.airline_reservation.repository.FlightRepository;
import com.example.airline_reservation.service.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl extends
    AbstractCrudService<FlightRequest, FlightResponse, FlightRepository, Flight,Long> implements FlightService {
  private final AirportRepository airportRepository;
  private final AirlineRepository airlineRepository;

  protected FlightServiceImpl(FlightRepository repository, AirportRepository airportRepository,
      AirlineRepository airlineRepository) {
    super(repository);
    this.airportRepository = airportRepository;
    this.airlineRepository = airlineRepository;
  }

  @Override
  protected Flight createAndSave(FlightRequest request) {
    //Arival Airport
    Airport arrivalAirport = airportRepository.findById(request.getArrivalAirportId()).orElseThrow(() -> new ResourceNotFoundException("Could not found airport with id "+request.getArrivalAirportId()));
    //Departure Airport
    Airport departureAirport = airportRepository.findById(request.getDepartureAirportId()).orElseThrow(() -> new ResourceNotFoundException("Could not found airport with id "+request.getArrivalAirportId()));

    //Airline
    Airline airline = airlineRepository.findById(request.getAirlinesId()).orElseThrow(() -> new ResourceNotFoundException("Could not found airline with id "+request.getAirlinesId()));

    return repository.save(Flight.builder()
            .flightNumber(request.getFlightNumber())
            .aircraftType(request.getAircraftType())
            .arrivalTime(request.getArrivalTime())
            .departureTime(request.getDepartureTime())
            .arrivalAirport(arrivalAirport)
            .departureAirport(departureAirport)
            .airline(airline)
        .build());
  }

  @Override
  protected Flight updateAndSave(Long id, FlightRequest request) {
    Flight flight = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not found flight with id "+id));
    flight.setFlightNumber(request.getFlightNumber());
    flight.setAircraftType(request.getAircraftType());
    flight.setArrivalTime(request.getArrivalTime());
    flight.setDepartureTime(request.getDepartureTime());
    return null;
  }

  @Override
  protected FlightResponse toResponse(Flight domainEntity) {
    FlightResponse response = new FlightResponse();
    response.setId(domainEntity.getId());
    response.setFlightNumber(domainEntity.getFlightNumber());
    response.setDepartureTime(domainEntity.getDepartureTime());
    response.setArrivalTime(domainEntity.getArrivalTime());
    response.setAircraftType(domainEntity.getAircraftType());
    response.setDepartureAirport(domainEntity.getDepartureAirport());
    response.setArrivalAirport(domainEntity.getArrivalAirport());
    response.setAirline(domainEntity.getAirline());
    return response;
  }
}
