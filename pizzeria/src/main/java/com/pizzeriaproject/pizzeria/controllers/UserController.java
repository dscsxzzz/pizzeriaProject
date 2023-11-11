package com.pizzeriaproject.pizzeria.controllers;

import com.pizzeriaproject.pizzeria.models.Dessert;
import com.pizzeriaproject.pizzeria.models.OrderDTO;
import com.pizzeriaproject.pizzeria.models.Pizza;
import com.pizzeriaproject.pizzeria.services.DessertService;
import com.pizzeriaproject.pizzeria.services.OrderService;
import com.pizzeriaproject.pizzeria.services.PizzaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final PizzaService pizzaService;

    private final DessertService dessertService;

    private final OrderService orderService;

    public UserController(PizzaService pizzaService, DessertService dessertService, OrderService orderService) {
        this.pizzaService = pizzaService;
        this.dessertService = dessertService;
        this.orderService = orderService;
    }

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

    //TODO change return type to ResponseEntity
    @PostMapping("/order")
    public OrderDTO newOrder(@RequestBody OrderDTO body) {
        orderService.addOrder(body.getPersonId(), body.getPizzaList(), body.getDessertList());
        return new OrderDTO(body.getPersonId(), body.getPizzaList(), body.getDessertList());
    }

    // TODO Change password
    // @PutMapping("/change-pass/{id}")
    // public

    // TODO Change user details
    // @PutMapping("/change-user-info/{id}")
    //
}
