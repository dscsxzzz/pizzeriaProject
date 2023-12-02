package com.pizzeriaproject.pizzeria.repository;

import com.pizzeriaproject.pizzeria.models.order.OrderItemPizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemPizzaRepository extends JpaRepository<OrderItemPizza, Long> {
    List<OrderItemPizza> findAllByOrderId(Long id);
}
