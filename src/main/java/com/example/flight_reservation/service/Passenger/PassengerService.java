package com.example.flight_reservation.service.Passenger;

import com.example.flight_reservation.dto.request.PassengerRequest;
import com.example.flight_reservation.dto.response.PassengerResponse;
import com.example.flight_reservation.entity.Passenger;
import com.example.flight_reservation.repository.PassengerRepository;
import com.example.flight_reservation.service.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class PassengerService extends AbstractCrudService<PassengerRequest, PassengerResponse, PassengerRepository, Passenger,Long> implements IPassengerService {

  protected PassengerService(PassengerRepository repository) {
    super(repository);
  }

  @Override
  protected Passenger createAndSave(PassengerRequest request) {
    return null;
  }

  @Override
  protected Passenger updateAndSave(Long id, PassengerRequest request) {
    return null;
  }

  @Override
  protected PassengerResponse toResponse(Passenger domainEntity) {
    PassengerResponse response = new PassengerResponse();
    response.setPassengerId(domainEntity.getPassengerId());
    response.setFirstName(domainEntity.getFirstName());
    response.setLastName(domainEntity.getLastName());
    response.setPassportNumber(domainEntity.getPassportNumber());
    response.setDateOfBirth(domainEntity.getDateOfBirth());
    return response;
  }
}
