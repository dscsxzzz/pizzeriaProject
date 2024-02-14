package com.pizzeriaproject.pizzeria.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Pizza")
@Table(name = "pizzas")
@Data
public class Pizza {
    @Id
    @SequenceGenerator(name = "pizza_sequence", sequenceName = "pizza_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizza_sequence")
    @Column(name = "pizza_id", unique = true, updatable = false)
    private Long id;
    private String name;
    private boolean veg;
    private double price;
    private String description;
    private String img;
    private int mediumPan;
    private int mediumstuffedcrustcheesemax;
    private int mediumstuffedcrustvegkebab;

    public Pizza() {
    }

    public Pizza(Long id, String name, boolean veg, double price, String description, String img, int mediumPan, int mediumstuffedcrustcheesemax, int mediumstuffedcrustvegkebab) {
        this.id = id;
        this.name = name;
        this.veg = veg;
        this.price = price;
        this.description = description;
        this.img = img;
        this.mediumPan = mediumPan;
        this.mediumstuffedcrustcheesemax = mediumstuffedcrustcheesemax;
        this.mediumstuffedcrustvegkebab = mediumstuffedcrustvegkebab;
    }

    public Pizza(String name, boolean veg, double price, String description, String img, int mediumPan, int mediumstuffedcrustcheesemax, int mediumstuffedcrustvegkebab) {
        this.name = name;
        this.veg = veg;
        this.price = price;
        this.description = description;
        this.img = img;
        this.mediumPan = mediumPan;
        this.mediumstuffedcrustcheesemax = mediumstuffedcrustcheesemax;
        this.mediumstuffedcrustvegkebab = mediumstuffedcrustvegkebab;
    }
}
