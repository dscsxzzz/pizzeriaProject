package com.pizzeriaproject.pizzeria.controllers;

import com.pizzeriaproject.pizzeria.models.order.Order;
import com.pizzeriaproject.pizzeria.models.user.ChangeUserDataDTO;
import com.pizzeriaproject.pizzeria.models.order.OrderDTO;
import com.pizzeriaproject.pizzeria.services.OrderService;
import com.pizzeriaproject.pizzeria.services.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "${spring.web.cors.allowed-origins}")
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


    @PostMapping("/order/{id}")
    public ResponseEntity<?> newOrder(@RequestBody OrderDTO order, @PathVariable Long id) {
        return orderService.addOrder(id, order.getPizzas(), order.getDesserts());
    }

    // TODO All user orders @GET
    @GetMapping("/order-get/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
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
