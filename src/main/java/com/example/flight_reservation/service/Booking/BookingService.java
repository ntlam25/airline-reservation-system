package com.example.flight_reservation.service.Booking;

import com.example.flight_reservation.dto.request.BookingRequest;
import com.example.flight_reservation.dto.request.BookingTicketTypeRequest;
import com.example.flight_reservation.dto.request.LuggageRequest;
import com.example.flight_reservation.dto.request.PassengerRequest;
import com.example.flight_reservation.dto.response.BookingResponse;
import com.example.flight_reservation.dto.response.PassengerResponse;
import com.example.flight_reservation.dto.response.UserResponse;
import com.example.flight_reservation.dto.response.VNPayResponse;
import com.example.flight_reservation.entity.*;
import com.example.flight_reservation.entity.Enums.BookingStatus;
import com.example.flight_reservation.entity.Enums.PaymentMethod;
import com.example.flight_reservation.entity.Enums.PaymentStatus;
import com.example.flight_reservation.exception.ResourceNotFoundException;
import com.example.flight_reservation.repository.BookingRepository;
import com.example.flight_reservation.repository.FlightRepository;
import com.example.flight_reservation.repository.PaymentRepository;
import com.example.flight_reservation.repository.TicketTypeRepository;
import com.example.flight_reservation.repository.UserRepository;
import com.example.flight_reservation.service.AbstractCrudService;
import com.example.flight_reservation.service.VNPay.VNPayService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class BookingService extends AbstractCrudService<BookingRequest, BookingResponse, BookingRepository, Booking,Long> implements IBookingService{

  private final BookingRepository bookingRepository;

  private final FlightRepository flightRepository;

  private final UserRepository userRepository;
  private final TicketTypeRepository ticketTypeRepository;
  private final VNPayService vnPayService;
  private final PaymentRepository paymentRepository;

  protected BookingService(BookingRepository repository, BookingRepository bookingRepository,
      FlightRepository flightRepository, UserRepository userRepository,
      TicketTypeRepository ticketTypeRepository, VNPayService vnPayService,
      PaymentRepository paymentRepository) {
    super(repository);
    this.bookingRepository = bookingRepository;
    this.flightRepository = flightRepository;
    this.userRepository = userRepository;
    this.ticketTypeRepository = ticketTypeRepository;
    this.vnPayService = vnPayService;
    this.paymentRepository = paymentRepository;
  }

  @Override
  protected Booking createAndSave(BookingRequest request) {
    User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    Flight flight = flightRepository.findById(request.getFlightId())
            .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

    Booking booking = new Booking();
    booking.setUser(user);
    booking.setFlight(flight);
    booking.setBookingDate(LocalDateTime.now());
    booking.setStatus(BookingStatus.PENDING);


    long totalPrice = Long.parseLong("0");

    for (BookingTicketTypeRequest ticketRequest : request.getBookingTicketTypes()) {
      TicketType ticketType = ticketTypeRepository.findById(ticketRequest.getTicketTypeId())
              .orElseThrow(() -> new ResourceNotFoundException("TicketType not found"));

      long ticketPrice = flight.getBasePrice()*ticketType.getPriceMultiplier()
              *ticketRequest.getQuantity();

      totalPrice = totalPrice + ticketPrice;

      BookingTicketType bookingTicketType = new BookingTicketType();
      bookingTicketType.setBooking(booking);
      bookingTicketType.setTicketType(ticketType);
      bookingTicketType.setQuantity(ticketRequest.getQuantity());

      booking.addBookingTicketType(bookingTicketType);
    }


    for (PassengerRequest passengerRequest : request.getPassengers()) {
      Passenger passenger = new Passenger();
      passenger.setBooking(booking);
      passenger.setFirstName(passengerRequest.getFirstName());
      passenger.setLastName(passengerRequest.getLastName());
      passenger.setPassportNumber(passengerRequest.getPassportNumber());
      passenger.setDateOfBirth(passengerRequest.getDateOfBirth());
      passenger.setSeatClass(passengerRequest.getSeatClass());
      booking.addPassenger(passenger);
    }

    for (LuggageRequest luggageRequest : request.getLuggage()) {
      Luggage luggage = new Luggage();
      luggage.setPrice(luggageRequest.getPrice());
      luggage.setWeight(luggageRequest.getWeight());
      luggage.setBooking(booking);
      totalPrice += luggageRequest.getPrice();

      booking.addLuggage(luggage);
    }

    booking.setTotalPrice(totalPrice);
    booking = bookingRepository.save(booking);
    return booking;
  }
  @Transactional
  public VNPayResponse createPaymentUrl(Long bookingId) {
    Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    Long totalPrice = booking.getTotalPrice();
    String paymentUrl = vnPayService.createPaymentUrl(
        booking.getBookingId(),
        totalPrice,
        "Payment for booking " + booking.getBookingId()
    );

    // 6. Update booking status to PENDING_PAYMENT
    booking.setStatus(BookingStatus.PENDING);
    bookingRepository.save(booking);
    return new VNPayResponse(booking.getBookingId(), "Booking created successfully",BookingStatus.PENDING, paymentUrl);
  }

  @Override
  public List<BookingResponse> findBookingsByUserId(Long userId) {
    List<BookingResponse> responses = new ArrayList<>();
    for(Booking booking : repository.findBookingByUserUserId(userId)){
      responses.add(toResponse(booking));
    }
    return responses;
  }

  @Transactional
  public VNPayResponse processPaymentCallback(Map<String, String> vnpayResponse) {
      String vnp_ResponseCode = vnpayResponse.get("vnp_ResponseCode");
      String vnp_TxnRef = vnpayResponse.get("vnp_TxnRef");
      Long bookingId = Long.parseLong(vnp_TxnRef);

      Booking booking = bookingRepository.findById(bookingId)
          .orElseThrow(() -> new RuntimeException("Booking not found"));

      if ("00".equals(vnp_ResponseCode)) {
        // Payment successful
        booking.setStatus(BookingStatus.CONFIRMED);

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(Double.parseDouble(vnpayResponse.get("vnp_Amount"))/100L);
        payment.setPaymentMethod(PaymentMethod.VNPAY);
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setTransactionId(vnpayResponse.get("vnp_TransactionNo"));
        paymentRepository.save(payment);

        VNPayResponse vnPayResponse = new VNPayResponse(bookingId, "Payment successful", BookingStatus.CONFIRMED);
        vnPayResponse.setPaymentDetails(new VNPayResponse.PaymentDetails(vnpayResponse.get("vnp_TransactionNo"),"VNPay",Long.parseLong(vnpayResponse.get("vnp_Amount"))/100L,LocalDateTime.now()));
        return vnPayResponse;
      } else {
        // Payment failed
        booking.setStatus(BookingStatus.PAYMENT_FAILED);
        return new VNPayResponse(bookingId, "Payment failed",BookingStatus.CANCELLED);
      }
  }

  @Override
  protected Booking updateAndSave(Long id, BookingRequest request) {
    return null;
  }

  @Override
  protected BookingResponse toResponse(Booking domainEntity) {
    BookingResponse response = new BookingResponse();
    response.setBookingId(domainEntity.getBookingId());
    response.setFlightId(domainEntity.getFlight().getFlightId());
    response.setUser(toDTO(domainEntity.getUser()));
    response.setStatus(domainEntity.getStatus());
    response.setBookingDate(domainEntity.getBookingDate());
    response.setPassengers(toDTO(domainEntity.getPassengers()));
    response.setTotalPrice(domainEntity.getTotalPrice());
    response.setLuggage(domainEntity.getLuggage());
    response.setBookingTicketType(domainEntity.getBookingTicketTypes());
    return response;
  }

  private List<PassengerResponse> toDTO(List<Passenger> passengers) {
    List<PassengerResponse> responses = new ArrayList<>();
    for (Passenger p : passengers) {
        PassengerResponse response = new PassengerResponse();
        response.setPassengerId(p.getPassengerId());
        response.setPassportNumber(p.getPassportNumber());
        response.setFirstName(p.getFirstName());
        response.setLastName(p.getLastName());
        response.setDateOfBirth(p.getDateOfBirth());
        responses.add(response);
    }
    return responses;
  }
  private UserResponse toDTO(User user) {
    UserResponse response = new UserResponse();
      response.setUserId(user.getUserId());
      response.setUsername(user.getUsername());
      response.setEmail(user.getEmail());
      response.setFirstName(user.getFirstName());
      response.setLastName(user.getLastName());
      response.setPhoneNumber(user.getPhoneNumber());
    return response;
  }
}
