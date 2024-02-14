package com.pizzeriaproject.pizzeria.models.order;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
    private List<OrderItemPizzaDTO> pizzas;
    private List<OrderItemDessertDTO> desserts;
}
