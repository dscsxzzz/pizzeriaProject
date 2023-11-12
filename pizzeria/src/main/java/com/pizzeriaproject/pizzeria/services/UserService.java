package com.pizzeriaproject.pizzeria.services;

import com.pizzeriaproject.pizzeria.models.User;
import com.pizzeriaproject.pizzeria.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In the user details service");

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User is not found"));
    }

    public ResponseEntity<?> changeUserDetails(Long id, String username, String email, String name, String surname, String phone, String address) {

        User user = userRepository.findById(id).map(
                user1 -> {
                    if (!user1.getUsername().equals(username)) {
                        if (userRepository.findByUsername(username).isEmpty())
                            user1.setUsername(username);
                        else {
                            throw new RuntimeException("The username " + username + " is taken! Please, try another one.");
                        }
                    }
                    if (!user1.getEmail().equals(email)) {
                        if (!userRepository.existsByEmail(email))
                            user1.setEmail(email);
                        else {
                            throw new RuntimeException("The email " + email + " is already used! Please, try another one.");
                        }
                    }
                    if (!user1.getPhone().equals(phone)) {
                        if (!userRepository.existsByPhone(phone))
                            user1.setPhone(phone);
                        else {
                            throw new RuntimeException("The phone number " + phone + " is already used! Please, try another one.");
                        }
                    }
                    user1.setName(name);
                    user1.setSurname(surname);
                    user1.setAddress(address);
                    return userRepository.save(user1);
                }
        ).orElseThrow(() -> new RuntimeException("User not found!"));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<?> changePassword(Long id, String password) {
        User user = userRepository.findById(id).map(
                user1 -> {
                    user1.setPassword(passwordEncoder.encode(password));
                    return userRepository.save(user1);
                }
        ).orElseThrow(() -> new RuntimeException("User not found!"));
        return new ResponseEntity<>(user, HttpStatusCode.valueOf(204));
    }
}
