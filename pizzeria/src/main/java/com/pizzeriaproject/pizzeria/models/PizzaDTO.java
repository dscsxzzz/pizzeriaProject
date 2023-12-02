package com.pizzeriaproject.pizzeria.models;

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
    private String img;
    private int mediumPan;
    private int mediumstuffedcrustcheesemax;
    private int mediumstuffedcrustvegkebab;
}
