package com.pizzeriaproject.pizzeria.controllers;

import com.pizzeriaproject.pizzeria.models.Pizza;
import com.pizzeriaproject.pizzeria.models.Dessert;
import com.pizzeriaproject.pizzeria.services.DessertService;
import com.pizzeriaproject.pizzeria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private DessertService dessertService;

    @GetMapping("/")
    public String helloUserController() {
        return "User acces level";
    }

    @GetMapping("/pizzas")
    public List<Pizza> pizzaList() {
        return pizzaService.allPizzas();
    }

    @GetMapping("/desserts")
    public List<Dessert> dessertList() {
        return dessertService.allDesserts();
    }
}
