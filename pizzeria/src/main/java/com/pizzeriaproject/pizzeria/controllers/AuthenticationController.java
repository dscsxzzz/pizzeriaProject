package com.pizzeriaproject.pizzeria.controllers;

import com.pizzeriaproject.pizzeria.models.RegistrationDTO;
import com.pizzeriaproject.pizzeria.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword(), body.getEmail(), body.getName(), body.getSurname(), body.getPhone(), body.getAddress());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody RegistrationDTO body) {
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
    
    @PostMapping(value = "/forgot-password/{username}")
    public ResponseEntity<?> forgotPasswordUser(@PathVariable String username) {
        return authenticationService.forgotPassword(username);
    }

    @PostMapping(value = "/forgot-password-code/{code}")
    public ResponseEntity<?> forgotPasswordCodeUser(@PathVariable String code) throws Exception {
        return authenticationService.checkCode(code);
    }
}
