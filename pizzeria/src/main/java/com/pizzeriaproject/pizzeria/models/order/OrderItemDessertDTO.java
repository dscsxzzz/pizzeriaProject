package com.pizzeriaproject.pizzeria.models.order;

import com.pizzeriaproject.pizzeria.models.DessertDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemDessertDTO {
    private DessertDTO dessert;
    private Integer amount;
    private Double price;
}
