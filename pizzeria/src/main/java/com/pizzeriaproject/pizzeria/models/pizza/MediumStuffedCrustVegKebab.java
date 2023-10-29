package com.pizzeriaproject.pizzeria.models.pizza;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class MediumStuffedCrustVegKebab {
    @Column(name = "medium_stuffed_crust_veg_kebab")
    private int price;

    public MediumStuffedCrustVegKebab() {
    }

    public MediumStuffedCrustVegKebab(int price) {
        this.price = price;
    }
}
