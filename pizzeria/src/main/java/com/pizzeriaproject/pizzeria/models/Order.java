package com.pizzeriaproject.pizzeria.models;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "orders")
@Entity
@Data
public class Order {
    //TODO
    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @Column(name = "order_id", unique = true, updatable = false)
    private Long id;
    private Long personId;
    private String pizzaList;
    private String dessertList;

    public Order() {
    }

    public Order(Long id, Long person_id, String pizzaList, String dessertList) {
        this.id = id;
        this.personId = person_id;
        this.pizzaList = pizzaList;
        this.dessertList = dessertList;
    }

    public Order(Long person_id, String pizzaList, String dessertList) {
        this.personId = person_id;
        this.pizzaList = pizzaList;
        this.dessertList = dessertList;
    }
}
