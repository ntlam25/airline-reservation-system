package com.example.airline_reservation.repository;

import com.example.airline_reservation.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Long> {

}
