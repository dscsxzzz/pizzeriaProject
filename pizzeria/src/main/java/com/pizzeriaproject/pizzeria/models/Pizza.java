package com.pizzeriaproject.pizzeria.models;

import com.pizzeriaproject.pizzeria.models.pizza.PizzaSizeAndCrust;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

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
    private int quantity;
    private String img;
    @Embedded
    private PizzaSizeAndCrust sizeandcrust;

    public Pizza() {
    }

    public Pizza(Long id, String name, boolean veg, double price, String description, int quantity, String img, PizzaSizeAndCrust sizeandcrust) {
        this.id = id;
        this.name = name;
        this.veg = veg;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.img = img;
        this.sizeandcrust = sizeandcrust;
    }

    public Pizza(String name, boolean veg, double price, String description, int quantity, String img, PizzaSizeAndCrust sizeandcrust) {
        this.name = name;
        this.veg = veg;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.img = img;
        this.sizeandcrust = sizeandcrust;
    }
}