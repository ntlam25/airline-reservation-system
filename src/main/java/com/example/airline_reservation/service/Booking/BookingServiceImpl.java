package com.example.airline_reservation.service.Booking;

import com.example.airline_reservation.dto.request.BookingRequest;
import com.example.airline_reservation.dto.response.BookingResponse;
import com.example.airline_reservation.dto.response.PassengerResponse;
import com.example.airline_reservation.entity.Account;
import com.example.airline_reservation.entity.Booking;
import com.example.airline_reservation.entity.Flight;
import com.example.airline_reservation.entity.Passenger;
import com.example.airline_reservation.entity.Seat;
import com.example.airline_reservation.exception.ResourceNotFoundException;
import com.example.airline_reservation.repository.AccountRepository;
import com.example.airline_reservation.repository.BookingRepository;
import com.example.airline_reservation.repository.FlightRepository;
import com.example.airline_reservation.repository.PassengerRepository;
import com.example.airline_reservation.repository.SeatRepository;
import com.example.airline_reservation.service.AbstractCrudService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl extends AbstractCrudService<BookingRequest, BookingResponse, BookingRepository, Booking,Long> implements BookingService {

  private final PassengerRepository passengerRepository;
  private final SeatRepository seatRepository;
  private final FlightRepository flightRepository;
  private final AccountRepository accountRepository;

  protected BookingServiceImpl(BookingRepository repository,
      PassengerRepository passengerRepository, SeatRepository seatRepository,
      FlightRepository flightRepository, AccountRepository accountRepository) {
    super(repository);
    this.passengerRepository = passengerRepository;
    this.seatRepository = seatRepository;
    this.flightRepository = flightRepository;
    this.accountRepository = accountRepository;
  }

  @Override
  protected Booking createAndSave(BookingRequest request) {
    Passenger passenger = passengerRepository.findById(request.getPassengerId()).orElseThrow(() -> new ResourceNotFoundException("Could not found passenger"));
    Flight flight = flightRepository.findById(request.getFlightId()).orElseThrow(() -> new ResourceNotFoundException("Could not found flight"));
    Seat seat = seatRepository.findById(request.getSeatId()).orElseThrow(() -> new ResourceNotFoundException("Could not found seat"));


    if (!seat.getStatus().equals("Available")){
      throw new IllegalStateException("Seat isn't available");
    }
    Booking booking = new Booking();
    if (request.getAccountId() != null){
      Account account = accountRepository.findById(request.getAccountId()).orElseThrow(() -> new ResourceNotFoundException("Could not found account"));
      booking.setAccount(account);
    }
    booking.setBookingDateTime(LocalDateTime.now());
    booking.setStatus(request.getStatus());
    booking.setPassenger(passenger);
    booking.setFlight(flight);
    booking.setSeat(seat);
    seat.setStatus("Booked");
    seatRepository.save(seat);
    return repository.save(booking);
  }

  @Override
  protected Booking updateAndSave(Long id, BookingRequest request) {
    Booking booking = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not found booking with id "+id));

    Passenger passenger = passengerRepository.findById(request.getPassengerId()).orElseThrow(() -> new ResourceNotFoundException("Could not found passenger"));
    Flight flight = flightRepository.findById(request.getFlightId()).orElseThrow(() -> new ResourceNotFoundException("Could not found flight"));
    Seat seat = seatRepository.findById(request.getSeatId()).orElseThrow(() -> new ResourceNotFoundException("Could not found seat"));


    if (!seat.getStatus().equals("Available")){
      throw new IllegalStateException("Seat isn't available");
    }
    if (request.getAccountId() != null){
      Account account = accountRepository.findById(request.getAccountId()).orElseThrow(() -> new ResourceNotFoundException("Could not found account"));
      booking.setAccount(account);
    }
    booking.setBookingDateTime(LocalDateTime.now());
    booking.setStatus(request.getStatus());
    booking.setPassenger(passenger);
    booking.setSeat(seat);
    booking.setFlight(flight);
    return repository.save(booking);
  }

  @Override
  protected BookingResponse toResponse(Booking domainEntity) {
    BookingResponse response = new BookingResponse();
    response.setId(domainEntity.getId());
    response.setBookingDate(domainEntity.getBookingDateTime());
    response.setStatus(domainEntity.getStatus());
    response.setPassenger(domainEntity.getPassenger());
    response.setFlight(domainEntity.getFlight());
    response.setSeat(domainEntity.getSeat());
    response.setAccount(domainEntity.getAccount());
    return response;
  }
  private PassengerResponse toDTO(Passenger passenger){
    PassengerResponse response = new PassengerResponse();
    response.setId(passenger.getId());
    response.setEmail(passenger.getEmail());
    response.setNationality(passenger.getNationality());
    response.setFirstName(passenger.getFirstName());
    response.setLastName(passenger.getLastName());
    response.setDateOfBirth(passenger.getDateOfBirth());
    response.setPassportNumber(passenger.getPassportNumber());
    response.setPhoneNumber(passenger.getPhoneNumber());
    return response;
  }
}
