package com.pizzeriaproject.pizzeria.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DessertDTO {
    private Long id;
    private String name;
    private double price;
    private String description;
    private String img;
}
