package com.example.flight_reservation.dto.request;

import com.example.flight_reservation.entity.Enums.UserRole;
import lombok.Data;

@Data
public class UserRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;
    private UserRole role;
}
