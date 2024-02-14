package com.pizzeriaproject.pizzeria.repository;

import com.pizzeriaproject.pizzeria.models.order.OrderItemDessert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemDessertRepository extends JpaRepository<OrderItemDessert, Long> {
    List<OrderItemDessert> findAllByOrderId(Long id);
}
