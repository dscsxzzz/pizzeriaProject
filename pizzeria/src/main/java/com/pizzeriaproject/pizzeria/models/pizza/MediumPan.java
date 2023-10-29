package com.pizzeriaproject.pizzeria.models.pizza;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class MediumPan {
    @Column(name = "medium_pan_prize")
    private int price;

    public MediumPan() {
    }

    public MediumPan(int price) {
        this.price = price;
    }
}
