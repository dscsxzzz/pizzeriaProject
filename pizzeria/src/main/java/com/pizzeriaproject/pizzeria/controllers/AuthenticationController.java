package com.pizzeriaproject.pizzeria.controllers;

import com.pizzeriaproject.pizzeria.models.LoginResponseDTO;
import com.pizzeriaproject.pizzeria.models.RegistrationDTO;
import com.pizzeriaproject.pizzeria.models.User;
import com.pizzeriaproject.pizzeria.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body) {
        return authenticationService.registerUser(body.getUsername(), body.getPassword(), body.getEmail(), body.getName(), body.getSurname(), body.getPhone(), body.getAddress());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body) {
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}
