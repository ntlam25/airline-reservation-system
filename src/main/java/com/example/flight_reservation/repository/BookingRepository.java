package com.example.flight_reservation.repository;

import com.example.flight_reservation.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>, JpaSpecificationExecutor<Booking> {

}
