package com.example.custom_jwt_authentication.security.model.requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String username;
    private String password;
    private String role; // Optional: ROLE_USER, ROLE_ADMIN
}

