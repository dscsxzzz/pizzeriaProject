package com.pizzeriaproject.pizzeria.services;

import com.pizzeriaproject.pizzeria.models.Pizza;
import com.pizzeriaproject.pizzeria.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> allPizzas() {
        return pizzaRepository.findAll();
    }
}
