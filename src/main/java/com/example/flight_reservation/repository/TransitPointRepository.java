package com.example.flight_reservation.repository;

import com.example.flight_reservation.entity.TransitPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitPointRepository extends JpaRepository<TransitPoint,Long> {
}
