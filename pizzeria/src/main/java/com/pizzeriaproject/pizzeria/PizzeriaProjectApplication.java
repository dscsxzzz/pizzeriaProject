package com.pizzeriaproject.pizzeria;

import com.pizzeriaproject.pizzeria.models.Role;
import com.pizzeriaproject.pizzeria.models.User;
import com.pizzeriaproject.pizzeria.repository.RoleRepository;
import com.pizzeriaproject.pizzeria.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PizzeriaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzeriaProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
        return args ->{
            if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User(1L, "admin", passwordEncode.encode("password"), "ostapnote2@gmmail.com",
                    "Admin", "Admin", "11111", "Nowhere", roles);

            userRepository.save(admin);
        };
    }
}
