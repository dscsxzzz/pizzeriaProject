package com.pizzeriaproject.pizzeria.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository) {
        return args -> {
//            repository.save(new Customer(
//                    "Ostapula",
//                    "passw0rd",
//                    "ostapnote2@gmail.com",
//                    "Ostap",
//                    "Zabanzhalo",
//                    "789697798",
//                    "MIkolajchyka"
//                )
//            );
        };
    }
}
