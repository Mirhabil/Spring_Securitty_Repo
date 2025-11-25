package com.example.custom_jwt_authentication.security.controller;

import com.example.custom_jwt_authentication.security.model.User;
import com.example.custom_jwt_authentication.security.model.requests.LoginRequest;
import com.example.custom_jwt_authentication.security.model.requests.RegisterRequest;
import com.example.custom_jwt_authentication.security.model.responses.TokenResponse;
import com.example.custom_jwt_authentication.security.service.JwtService;
import com.example.custom_jwt_authentication.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        UserDetails user = (UserDetails) auth.getPrincipal();

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        User newUser = userService.register(request);

        return ResponseEntity.ok("User registered successfully: " + newUser.getUsername());
    }

}

