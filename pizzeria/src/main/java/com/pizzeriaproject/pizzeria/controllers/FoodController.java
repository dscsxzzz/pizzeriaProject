package com.pizzeriaproject.pizzeria.controllers;

import com.pizzeriaproject.pizzeria.models.Dessert;
import com.pizzeriaproject.pizzeria.models.Pizza;
import com.pizzeriaproject.pizzeria.services.DessertService;
import com.pizzeriaproject.pizzeria.services.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("")
@RestController
@CrossOrigin(origins = "${spring.web.cors.allowed-origins}")
public class FoodController {

    private final PizzaService pizzaService;

    private final DessertService dessertService;

    public FoodController(PizzaService pizzaService, DessertService dessertService) {
        this.pizzaService = pizzaService;
        this.dessertService = dessertService;
    }

    @GetMapping("/pizzas")
    public ResponseEntity<List<Pizza>> pizzaList() {
        return new ResponseEntity<>(pizzaService.allPizzas(), HttpStatus.OK);
    }

    @GetMapping("/desserts")
    public ResponseEntity<List<Dessert>> dessertList() {
        return new ResponseEntity<>(dessertService.allDesserts(), HttpStatus.OK);
    }
}
