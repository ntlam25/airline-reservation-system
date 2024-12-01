package com.example.flight_reservation.service.User;

import com.example.flight_reservation.dto.request.UserRequest;
import com.example.flight_reservation.dto.response.UserResponse;
import com.example.flight_reservation.entity.Enums.UserRole;
import com.example.flight_reservation.entity.User;
import com.example.flight_reservation.exception.ResourceNotFoundException;
import com.example.flight_reservation.repository.UserRepository;
import com.example.flight_reservation.service.AbstractCrudService;

import com.example.flight_reservation.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractCrudService<UserRequest, UserResponse, UserRepository, User,Long> implements IUserService{
    protected UserService(UserRepository repository, JWTService jwtService) {
        super(repository);
        this.jwtService = jwtService;
    }
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Override
    protected User createAndSave(UserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setPasswordHash(encoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());
        return repository.save(user);
    }

    @Override
    protected User updateAndSave(Long id, UserRequest request) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not found user with id "+id));
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        if(request.getPassword() != null && !request.getPassword().isEmpty()){
            user.setPasswordHash(encoder.encode(request.getPassword()));
        }
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
        response.setUsername(domainEntity.getUsername());
        response.setPhoneNumber(domainEntity.getPhoneNumber());
        response.setRole(domainEntity.getRole());
        return response;
    }

    @Override
    public UserResponse findUserByEmail(String email) {
        return toResponse(repository.findUserByEmail(email));
    }

    @Override
    public String verify(UserRequest user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "Login Failed";
        }
    }
}
