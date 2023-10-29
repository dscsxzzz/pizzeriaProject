package com.pizzeriaproject.pizzeria.models.pizza;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
@Embeddable
public class PizzaSizeAndCrust {
    @Embedded
    private MediumPan mediumPan;
    @Embedded
    private MediumStuffedCrustCheeseMax mediumstuffedcrustcheesemax;
    @Embedded
    private MediumStuffedCrustVegKebab mediumstuffedcrustvegkebab;
    @Embedded
    private MediumStuffedCrustChickenSeekhKebab mediumstuffedcrustchickenseekhkebab;

    public PizzaSizeAndCrust() {
    }

    public PizzaSizeAndCrust(MediumPan mediumPan, MediumStuffedCrustCheeseMax mediumstuffedcrustcheesemax, MediumStuffedCrustVegKebab mediumstuffedcrustvegkebab) {
        this.mediumPan = mediumPan;
        this.mediumstuffedcrustcheesemax = mediumstuffedcrustcheesemax;
        this.mediumstuffedcrustvegkebab = mediumstuffedcrustvegkebab;
    }

    public PizzaSizeAndCrust(MediumPan mediumPan, MediumStuffedCrustCheeseMax mediumstuffedcrustcheesemax, MediumStuffedCrustChickenSeekhKebab mediumstuffedcrustchickenseekhkebab) {
        this.mediumPan = mediumPan;
        this.mediumstuffedcrustcheesemax = mediumstuffedcrustcheesemax;
        this.mediumstuffedcrustchickenseekhkebab = mediumstuffedcrustchickenseekhkebab;
    }
}
