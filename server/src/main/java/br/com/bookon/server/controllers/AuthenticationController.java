package br.com.bookon.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bookon.server.payload.request.postgres.LoginRequest;
import br.com.bookon.server.payload.request.postgres.RegisterRequest;
import br.com.bookon.server.payload.response.postgres.JwtResponse;
import br.com.bookon.server.payload.response.postgres.MessageResponse;
import br.com.bookon.server.services.AuthenticationService;
import br.com.bookon.server.services.UserService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    AuthenticationService authService;
    
    @Autowired
    private UserService userService;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponse> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.authenticate(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest signUpRequest) {
        return new ResponseEntity<>(userService.register(signUpRequest), HttpStatus.OK);
    }

}
