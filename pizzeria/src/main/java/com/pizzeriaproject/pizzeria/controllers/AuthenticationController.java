package com.pizzeriaproject.pizzeria.controllers;

import com.pizzeriaproject.pizzeria.models.user.ChangeUserDataDTO;
import com.pizzeriaproject.pizzeria.models.user.RegistrationDTO;
import com.pizzeriaproject.pizzeria.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "${spring.web.cors.allowed-origins}")
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
        System.out.println(username);
        return authenticationService.forgotPassword(username);
    }

    @PostMapping(value = "/forgot-password-code/{code}")
    public ResponseEntity<?> forgotPasswordCodeUser(@PathVariable String code) {
        return authenticationService.checkCode(code);
    }

    @PutMapping("/change-pass/{id}")
    public ResponseEntity<?> changeUserPassword(@RequestBody ChangeUserDataDTO body, @PathVariable Long id) {
        return authenticationService.changePassword(id, body.getPassword());
    }
}
