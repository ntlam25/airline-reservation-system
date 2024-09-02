package com.example.airline_reservation.repository;

import com.example.airline_reservation.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Long> {

}
