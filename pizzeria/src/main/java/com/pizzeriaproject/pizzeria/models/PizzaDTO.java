package com.pizzeriaproject.pizzeria.models;

import com.pizzeriaproject.pizzeria.models.pizza.PizzaSizeAndCrust;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PizzaDTO {
    private Long id;
    private String name;
    private boolean veg;
    private double price;
    private String description;
    private int quantity;
    private String img;
    private PizzaSizeAndCrust sizeandcrust;
}
