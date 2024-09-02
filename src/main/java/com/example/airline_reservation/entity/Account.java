package com.example.airline_reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "Username",nullable = false)
  private String username;
  @Column(name = "Password",nullable = false)
  private String password;
  @Column(name = "Email",nullable = false)
  private String email;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id",nullable = false)
  private Role role;

  @JsonBackReference
  @OneToMany(mappedBy = "account")
  private List<Booking> bookings;
}
