package com.example.flight_reservation.service.Airport;

import com.example.flight_reservation.dto.request.AirportRequest;
import com.example.flight_reservation.dto.response.AirportResponse;
import com.example.flight_reservation.entity.Airport;
import com.example.flight_reservation.exception.ResourceNotFoundException;
import com.example.flight_reservation.repository.AirportRepository;
import com.example.flight_reservation.service.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class AirportService extends AbstractCrudService<AirportRequest, AirportResponse, AirportRepository, Airport,Long> implements IAirportService {


  protected AirportService(AirportRepository repository) {
    super(repository);
  }

  @Override
  protected Airport createAndSave(AirportRequest request) {
    Airport airport = new Airport();
    airport.setAirportName(request.getName());
    airport.setAirportCode(request.getCode());
    airport.setCity(request.getCity());
    airport.setCountry(request.getCountry());
    return repository.save(airport);
  }

  @Override
  protected Airport updateAndSave(Long id, AirportRequest request) {
    Airport airport = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not found airport with id "+id));
    airport.setAirportName(request.getName());
    airport.setCity(request.getCity());
    airport.setCountry(request.getCountry());
    airport.setAirportCode(request.getCode());
    return repository.save(airport);
  }

  @Override
  protected AirportResponse toResponse(Airport domainEntity) {
    AirportResponse response = new AirportResponse();
    response.setId(domainEntity.getAirportId());
    response.setName(domainEntity.getAirportName());
    response.setCode(domainEntity.getAirportCode());
    response.setCity(domainEntity.getCity());
    response.setCountry(domainEntity.getCountry());
    return response;
  }
}
