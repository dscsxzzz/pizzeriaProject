package com.pizzeriaproject.pizzeria.repository;

import com.pizzeriaproject.pizzeria.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //TODO
}
