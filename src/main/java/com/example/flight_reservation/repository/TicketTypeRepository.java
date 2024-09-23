package com.example.flight_reservation.repository;

import com.example.flight_reservation.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType,Long> {

}
