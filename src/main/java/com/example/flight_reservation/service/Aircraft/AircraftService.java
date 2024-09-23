package com.example.flight_reservation.service.Aircraft;

import com.example.flight_reservation.dto.request.AircraftRequest;
import com.example.flight_reservation.dto.response.AircraftResponse;
import com.example.flight_reservation.entity.Aircraft;
import com.example.flight_reservation.exception.ResourceNotFoundException;
import com.example.flight_reservation.repository.AircraftRepository;
import com.example.flight_reservation.service.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class AircraftService extends AbstractCrudService<AircraftRequest, AircraftResponse, AircraftRepository, Aircraft,Long> implements IAircraftService {

    protected AircraftService(AircraftRepository repository) {
        super(repository);
    }

    @Override
    protected Aircraft createAndSave(AircraftRequest request) {
        Aircraft aircraft = new Aircraft();
        aircraft.setModel(request.getModel());
        aircraft.setTotalSeats(request.getTotalSeat());
        return repository.save(aircraft);
    }

    @Override
    protected Aircraft updateAndSave(Long id, AircraftRequest request) {
        Aircraft aircraft = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not found aircraft with id "+id));
        aircraft.setModel(request.getModel());
        aircraft.setTotalSeats(request.getTotalSeat());
        return repository.save(aircraft);
    }

    @Override
    protected AircraftResponse toResponse(Aircraft domainEntity) {
        AircraftResponse response = new AircraftResponse();
        response.setId(domainEntity.getAircraftId());
        response.setModel(domainEntity.getModel());
        response.setTotalSeat(domainEntity.getTotalSeats());
        return response;
    }
}
