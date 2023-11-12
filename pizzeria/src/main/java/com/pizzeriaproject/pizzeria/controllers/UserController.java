package com.pizzeriaproject.pizzeria.controllers;

import com.pizzeriaproject.pizzeria.models.ChangeUserDataDTO;
import com.pizzeriaproject.pizzeria.models.OrderDTO;
import com.pizzeriaproject.pizzeria.services.OrderService;
import com.pizzeriaproject.pizzeria.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final OrderService orderService;

    private final UserService userService;

    public UserController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String helloUserController() {
        return "User access level";
    }

    //TODO change return type to ResponseEntity
    @PostMapping("/order")
    public OrderDTO newOrder(@RequestBody OrderDTO body) {
        orderService.addOrder(body.getPersonId(), body.getPizzaList(), body.getDessertList());
        return new OrderDTO(body.getPersonId(), body.getPizzaList(), body.getDessertList());
    }

    @PutMapping("/change-user-info/{id}")
    public ResponseEntity<?> changeUserInformation(@RequestBody ChangeUserDataDTO body, @PathVariable Long id) {
        return userService.changeUserDetails(id, body.getUsername(), body.getEmail(), body.getName(), body.getSurname(), body.getPhone(), body.getAddress());
    }

    @PutMapping("/change-pass/{id}")
    public ResponseEntity<?> changeUserPassword(@RequestBody ChangeUserDataDTO body, @PathVariable Long id) {
        return userService.changePassword(id, body.getPassword());
    }
}
