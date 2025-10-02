package com.microservice_authenticate.controller;

import com.microservice_authenticate.client.dto.post.UserPostDto;
import com.microservice_authenticate.dto.RegisterUserDto;
import com.microservice_authenticate.service.AuthenticationService;
import com.microservice_authenticate.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class RegisterController {

    private final AuthenticationService authenticationService;

    public RegisterController(JwtService jwtService, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<UserPostDto> register(@RequestBody RegisterUserDto registerUserDto) {
        return authenticationService.signup(registerUserDto);
    }
}
