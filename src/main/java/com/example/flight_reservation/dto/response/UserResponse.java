package com.example.flight_reservation.dto.response;

import com.example.flight_reservation.entity.Enums.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;
    private UserRole role;
}
