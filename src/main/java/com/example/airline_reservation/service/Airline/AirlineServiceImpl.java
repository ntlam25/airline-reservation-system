package com.example.airline_reservation.service.Airline;

import com.example.airline_reservation.dto.request.AirlineRequest;
import com.example.airline_reservation.dto.response.AirlineResponse;
import com.example.airline_reservation.entity.Airline;
import com.example.airline_reservation.exception.ResourceNotFoundException;
import com.example.airline_reservation.repository.AirlineRepository;
import com.example.airline_reservation.service.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class AirlineServiceImpl extends
    AbstractCrudService<AirlineRequest, AirlineResponse, AirlineRepository, Airline,Long> implements
    AirlineService {

  protected AirlineServiceImpl(AirlineRepository repository) {
    super(repository);
  }

  @Override
  protected Airline createAndSave(AirlineRequest request) {
    return repository.save(Airline.builder()
            .code(request.getCode())
            .name(request.getName())
        .build());
  }

  @Override
  protected Airline updateAndSave(Long id, AirlineRequest request) {
    Airline airline = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not found airline with id "+id));
    airline.setName(request.getName());
    airline.setCode(request.getCode());
    return repository.save(airline);
  }


  @Override
  protected AirlineResponse toResponse(Airline domainEntity) {
    AirlineResponse response = new AirlineResponse();
    response.setId(domainEntity.getId());
    response.setCode(domainEntity.getCode());
    response.setName(domainEntity.getName());
    return response;
  }
}
