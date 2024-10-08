package com.example.flight_reservation.service.User;

import com.example.flight_reservation.dto.request.UserRequest;
import com.example.flight_reservation.dto.response.UserResponse;
import com.example.flight_reservation.entity.Enums.UserRole;
import com.example.flight_reservation.entity.User;
import com.example.flight_reservation.exception.ResourceNotFoundException;
import com.example.flight_reservation.repository.UserRepository;
import com.example.flight_reservation.service.AbstractCrudService;

import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractCrudService<UserRequest, UserResponse, UserRepository, User,Long> implements IUserService{
    protected UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    protected User createAndSave(UserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setPasswordHash(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(UserRole.CUSTOMER);
        return repository.save(user);
    }

    @Override
    protected User updateAndSave(Long id, UserRequest request) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not found user with id "+id));
        user.setLastName(request.getLastName());
        user.setPasswordHash(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setPhoneNumber(request.getPhoneNumber());
        return repository.save(user);
    }

    @Override
    protected UserResponse toResponse(User domainEntity) {
        UserResponse response = new UserResponse();
        response.setId(domainEntity.getUserId());
        response.setPassword(domainEntity.getPasswordHash());
        response.setEmail(domainEntity.getEmail());
        response.setFirstName(domainEntity.getFirstName());
        response.setLastName(domainEntity.getLastName());
        response.setRole(domainEntity.getRole());
        response.setPhoneNumber(domainEntity.getPhoneNumber());
        return response;
    }

    @Override
    public UserResponse findUserByEmail(String email) {
        return toResponse(repository.findUserByEmail(email));
    }
}
