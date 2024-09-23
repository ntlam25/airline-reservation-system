package com.example.flight_reservation.repository;

import com.example.flight_reservation.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long>, JpaSpecificationExecutor<Flight> {

}
