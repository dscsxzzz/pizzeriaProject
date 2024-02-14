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

    public Dessert() {
    }

    public Dessert(Long id, String name, double price, String description, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
    }

    public Dessert(String name, double price, String description, String img) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.img = img;
    }
}
