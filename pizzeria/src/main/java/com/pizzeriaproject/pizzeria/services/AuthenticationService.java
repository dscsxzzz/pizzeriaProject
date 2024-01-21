package com.pizzeriaproject.pizzeria.services;

import com.pizzeriaproject.pizzeria.models.user.ForgotPasswordResponseDTO;
import com.pizzeriaproject.pizzeria.models.user.LoginResponseDTO;
import com.pizzeriaproject.pizzeria.models.user.Role;
import com.pizzeriaproject.pizzeria.models.user.User;
import com.pizzeriaproject.pizzeria.repository.RoleRepository;
import com.pizzeriaproject.pizzeria.repository.UserRepository;
import com.pizzeriaproject.pizzeria.utils.GlobalExceptionHandler;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final EmailService emailService;

    private final CodeService codeService;

    private final GlobalExceptionHandler globalExceptionHandler;

    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService, EmailService emailService, CodeService codeService, GlobalExceptionHandler globalExceptionHandler) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.codeService = codeService;
        this.globalExceptionHandler = globalExceptionHandler;
    }

    public ResponseEntity<?> registerUser(String username, String password, String email, String name, String surname, String phone, String address) {
        try {
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
            log.info("New user registered {}", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return globalExceptionHandler.handleAllExceptions(e);
        }
    }

    public ResponseEntity<?> loginUser(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenService.generateJwt(auth);
            User user = userRepository.findByUsername(username).get();
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(user.getId(), username, user.getPassword(), user.getEmail(), user.getName(), user.getSurname(), user.getPhone(), user.getAddress(), token);
            log.info("User login {}", loginResponseDTO);
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return globalExceptionHandler.handleAllExceptions(e);
        }
    }

    public ResponseEntity<?> forgotPassword(String username) {
        try {
            User user = userRepository.findByUsername(username).get();
            emailService.sendHtmlMessage(user.getEmail(), "Password Reset Request", codeService.generateCode());
            ForgotPasswordResponseDTO forgotPasswordResponseDTO = new ForgotPasswordResponseDTO("Email was sent", user.getId());
            return new ResponseEntity<>(forgotPasswordResponseDTO, HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            return globalExceptionHandler.handleAllExceptions(ex);
        }
    }

    public ResponseEntity<?> checkCode(String code) {
        try {
            if (codeService.isCodeValid(code)) {
                ForgotPasswordResponseDTO forgotPasswordResponseDTO = new ForgotPasswordResponseDTO("Email was sent");
                return new ResponseEntity<>(forgotPasswordResponseDTO, HttpStatusCode.valueOf(200));
            } else {
                ForgotPasswordResponseDTO forgotPasswordResponseDTO = new ForgotPasswordResponseDTO("The code is expired");
                return new ResponseEntity<>(forgotPasswordResponseDTO, HttpStatusCode.valueOf(401));
            }
        } catch (Exception e) {
            return globalExceptionHandler.handleAllExceptions(e);
        }
    }
    public ResponseEntity<?> changePassword(Long id, String password) {
        User user = userRepository.findById(id).map(
                user1 -> {
                    user1.setPassword(passwordEncoder.encode(password));
                    return userRepository.save(user1);
                }
        ).orElseThrow(() -> new RuntimeException("User not found!"));
        return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
    }
}
