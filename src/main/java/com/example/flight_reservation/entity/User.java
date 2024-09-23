package com.example.flight_reservation.entity;


import com.example.flight_reservation.entity.Enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(unique = true, nullable = false, length = 50)
  private String username;

  @Column(unique = true, nullable = false, length = 100)
  private String email;

  @Column(nullable = false)
  private String passwordHash;

  @Column(nullable = false, length = 50)
  private String firstName;

  @Column(nullable = false, length = 50)
  private String lastName;

  @Column(length = 20)
  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private UserRole role = UserRole.CUSTOMER;

}