package com.pizzeriaproject.pizzeria.services;

import com.pizzeriaproject.pizzeria.models.Pizza;
import com.pizzeriaproject.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> allPizzas() {
        return pizzaRepository.findAll();
    }
}
