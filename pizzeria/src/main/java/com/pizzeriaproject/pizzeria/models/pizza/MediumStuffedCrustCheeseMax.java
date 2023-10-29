package com.pizzeriaproject.pizzeria.models.pizza;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class MediumStuffedCrustCheeseMax {
    @Column(name = "medium_stuffed_crust_cheese_max")
    private int price;

    public MediumStuffedCrustCheeseMax() {
    }

    public MediumStuffedCrustCheeseMax(int price) {
        this.price = price;
    }
}
