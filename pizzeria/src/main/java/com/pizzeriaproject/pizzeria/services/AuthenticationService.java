package com.pizzeriaproject.pizzeria.services;

import com.pizzeriaproject.pizzeria.models.LoginResponseDTO;
import com.pizzeriaproject.pizzeria.models.Role;
import com.pizzeriaproject.pizzeria.models.User;
import com.pizzeriaproject.pizzeria.repository.RoleRepository;
import com.pizzeriaproject.pizzeria.repository.UserRepository;
import com.pizzeriaproject.pizzeria.utils.GlobalExceptionHandler;
import jakarta.transaction.Transactional;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final GlobalExceptionHandler globalExceptionHandler;

    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService, GlobalExceptionHandler globalExceptionHandler) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.globalExceptionHandler = globalExceptionHandler;
    }

    public ResponseEntity<?> registerUser(String username, String password, String email, String name, String surname, String phone, String address) {
        try {
            //Temporary UGLY decision of validation of data
            if (userRepository.findByUsername(username).isPresent()) {
                throw new DuplicateKeyException("The username " + username + " is taken! Please, try another one.");
            } else if (userRepository.existsByEmail(email)) {
                throw new DuplicateKeyException("The email " + email + " is already used! Please, try another one.");
            } else if (userRepository.existsByPhone(phone)) {
                throw new DuplicateKeyException("The phone number " + phone + " is already used! Please, try another one.");
            }

            String encodedPassword = passwordEncoder.encode(password);
            Role userRole = roleRepository.findByAuthority("USER").get();

            Set<Role> authorities = new HashSet<>();

            authorities.add(userRole);

            User user = userRepository.save(new User(username, encodedPassword, email, name, surname, phone, address, authorities));
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            // TODO logging
            System.out.println(e.getMessage() + " - ERROR MESSAGE ");
            return globalExceptionHandler.handleAllExceptions(e);
        }
    }

    public ResponseEntity<?> loginUser(String username, String password) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            // TODO to understand how to work with JWT
            String token = tokenService.generateJwt(auth);
            User user = userRepository.findByUsername(username).get();
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(user.getId(), username, user.getPassword(), user.getEmail(), user.getName(), user.getSurname(), user.getPhone(), user.getAddress());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("New-User-login", "user-{" + username + "} logged in");
            return new ResponseEntity<>(loginResponseDTO, responseHeaders, HttpStatus.OK);

        } catch (AuthenticationException e) {
            // TODO logging
            System.out.println(e.getMessage() + " " + username);
            return globalExceptionHandler.handleAllExceptions(e);
        }
    }
}
