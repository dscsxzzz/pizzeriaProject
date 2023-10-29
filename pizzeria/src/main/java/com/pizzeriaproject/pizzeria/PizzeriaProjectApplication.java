package com.pizzeriaproject.pizzeria;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pizzeriaproject.pizzeria.models.Dessert;
import com.pizzeriaproject.pizzeria.models.Pizza;
import com.pizzeriaproject.pizzeria.models.Role;
import com.pizzeriaproject.pizzeria.models.User;
import com.pizzeriaproject.pizzeria.repository.DessertRepository;
import com.pizzeriaproject.pizzeria.repository.PizzaRepository;
import com.pizzeriaproject.pizzeria.repository.RoleRepository;
import com.pizzeriaproject.pizzeria.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class PizzeriaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzeriaProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode, PizzaRepository pizzaRepository, DessertRepository dessertRepository) {
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User admin = new User(1L, "admin", passwordEncode.encode("adminpassw0rd"), "ostapnote2@gmail.com",
                    "Ostap", "Zaba", "+48789697798", "Mikołajczyka 16", roles);

            userRepository.save(admin);

            String jsonFilePath = "C:/Users/ostap/IdeaProjects/pizzeria/pizzas.json";
            Gson gson = new Gson();
            try (Reader reader = Files.newBufferedReader(Paths.get(jsonFilePath))) {

                List<Pizza> pizzas = gson.fromJson(reader, new TypeToken<List<Pizza>>() {
                }.getType());

                for (Pizza pizza1 : pizzas) {
                    System.out.println(pizza1);
                }
                pizzaRepository.saveAll(pizzas);
            }

            String jsonFilePathDesserts = "C:/Users/ostap/IdeaProjects/pizzeria/desserts.json";
            try (Reader reader = Files.newBufferedReader(Paths.get(jsonFilePathDesserts))) {

                List<Dessert> desserts = gson.fromJson(reader, new TypeToken<List<Dessert>>() {
                }.getType());

                for (Dessert dessert : desserts) {
                    System.out.println(dessert);
                }
                dessertRepository.saveAll(desserts);
            }
        };
    }
}
