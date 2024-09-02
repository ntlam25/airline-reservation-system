package com.example.airline_reservation.service.Airport;

import com.example.airline_reservation.dto.request.AirportRequest;
import com.example.airline_reservation.dto.response.AirportResponse;
import com.example.airline_reservation.entity.Airport;
import com.example.airline_reservation.exception.ResourceNotFoundException;
import com.example.airline_reservation.repository.AirportRepository;
import com.example.airline_reservation.service.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class AirportServiceImpl extends
    AbstractCrudService<AirportRequest, AirportResponse,AirportRepository, Airport,Long> implements AirportService {

  protected AirportServiceImpl(AirportRepository repository) {
    super(repository);
  }

  @Override
  protected Airport createAndSave(AirportRequest request) {
    return repository.save(Airport.builder()
            .name(request.getName())
            .code(request.getCode())
            .city(request.getCity())
            .country(request.getCountry())
        .build());
  }

  @Override
  protected Airport updateAndSave(Long id, AirportRequest request) {
    Airport airport = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not found airport with id "+id));
    airport.setName(request.getName());
    airport.setCity(request.getCity());
    airport.setCountry(request.getCountry());
    airport.setCode(request.getCode());
    return repository.save(airport);
  }

  @Override
  protected AirportResponse toResponse(Airport domainEntity) {
    AirportResponse response = new AirportResponse();
    response.setId(domainEntity.getId());
    response.setCity(domainEntity.getCity());
    response.setCountry(domainEntity.getCountry());
    response.setCode(domainEntity.getCode());
    response.setName(domainEntity.getName());
    return response;
  }

}
