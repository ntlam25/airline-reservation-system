package com.example.flight_reservation.repository;

import com.example.flight_reservation.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long>, JpaSpecificationExecutor<Flight> {
    @Query("SELECT f FROM Flight f WHERE f.departureAirport.airportCode = :depAirport AND f.arrivalAirport.airportCode = :arrAirport AND DATE(f.departureTime) = :depDate")
    List<Flight> findFlights(
            @Param("depAirport") String depAirport,
            @Param("arrAirport") String arrAirport,
            @Param("depDate") LocalDate depDate);
}
