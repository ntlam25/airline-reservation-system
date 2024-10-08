package com.example.flight_reservation.service.User;

import com.example.flight_reservation.dto.request.UserRequest;
import com.example.flight_reservation.dto.response.UserResponse;
import com.example.flight_reservation.entity.User;
import com.example.flight_reservation.service.BaseCRUDService;

public interface IUserService extends BaseCRUDService<UserRequest, UserResponse, User,Long> {
    UserResponse findUserByEmail(String email);
}
