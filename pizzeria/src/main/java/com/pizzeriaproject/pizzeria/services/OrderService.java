package com.pizzeriaproject.pizzeria.services;

import com.pizzeriaproject.pizzeria.models.Order;
import com.pizzeriaproject.pizzeria.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    //TODO
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Long personId, String pizzaList, String dessertList) {
        orderRepository.save(new Order(personId, pizzaList, dessertList));
    }
}
