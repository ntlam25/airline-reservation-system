package com.example.airline_reservation.repository;

import com.example.airline_reservation.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
