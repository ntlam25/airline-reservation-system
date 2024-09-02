package com.example.airline_reservation.repository;

import com.example.airline_reservation.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline,Long> {

}
