package com.pizzeriaproject.pizzeria.models.pizza;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class MediumStuffedCrustChickenSeekhKebab {
    @Column(name = "medium_stuffed_crust_chicken_seekh_kebab")
    private int price;

    public MediumStuffedCrustChickenSeekhKebab() {
    }

    public MediumStuffedCrustChickenSeekhKebab(int price) {
        this.price = price;
    }
}
