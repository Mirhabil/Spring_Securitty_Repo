package com.example.custom_jwt_authentication.security.model.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String username;
    private String password;
}

