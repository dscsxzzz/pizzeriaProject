package com.pizzeriaproject.pizzeria.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
    //TODO
    private Long personId;
    private String pizzaList;
    private String dessertList;
}
