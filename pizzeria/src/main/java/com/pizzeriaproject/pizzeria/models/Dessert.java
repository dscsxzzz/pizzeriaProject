package com.pizzeriaproject.pizzeria.models;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "desserts")
@Entity(name = "Dessert")
@Data
public class Dessert {
    @Id
    @SequenceGenerator(name = "dessert_sequence", sequenceName = "dessert_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dessert_sequence")
    @Column(name = "dessert_id", unique = true, updatable = false)
    private Long id;
    private String name;
    private double price;
    private String description;
    private String img;
    private int quantity;

    public Dessert() {
    }

    public Dessert(Long id, String name, double price, String description, String img, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.quantity = quantity;
    }

    public Dessert(String name, double price, String description, String img, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
        this.quantity = quantity;
    }
}
