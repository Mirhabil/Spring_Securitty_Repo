package com.example.custom_jwt_authentication.security.service;

import com.example.custom_jwt_authentication.security.model.User;
import com.example.custom_jwt_authentication.security.model.requests.RegisterRequest;
import com.example.custom_jwt_authentication.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())) // BCrypt burada
                .role(request.getRole() != null ? request.getRole() : "ROLE_USER")
                .build();

        return userRepository.save(user);
    }
}

