package com.example.flight_reservation.repository;

import com.example.flight_reservation.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft,Long> {
}
