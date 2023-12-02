package com.pizzeriaproject.pizzeria.models.order;

import com.pizzeriaproject.pizzeria.models.PizzaDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemPizzaDTO {
    private PizzaDTO pizza;
    private Integer amount;
    private Double price;
    private String size;
}
