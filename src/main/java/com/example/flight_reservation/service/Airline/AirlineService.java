package com.example.flight_reservation.service.Airline;

import com.example.flight_reservation.dto.request.AirlineRequest;
import com.example.flight_reservation.dto.response.AirlineResponse;
import com.example.flight_reservation.entity.Airline;
import com.example.flight_reservation.exception.ResourceNotFoundException;
import com.example.flight_reservation.repository.AirlineRepository;
import com.example.flight_reservation.service.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class AirlineService extends AbstractCrudService<AirlineRequest, AirlineResponse, AirlineRepository, Airline,Long> implements IAirlineService {

    protected AirlineService(AirlineRepository repository) {
        super(repository);
    }

    @Override
    protected Airline createAndSave(AirlineRequest request) {
        Airline airline = new Airline();
        airline.setName(airline.getName());
        airline.setCode(airline.getCode());
        return repository.save(airline);
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
        response.setId(domainEntity.getAirlineId());
        response.setName(domainEntity.getName());
        response.setCode(domainEntity.getCode());
        return response;
    }
}
