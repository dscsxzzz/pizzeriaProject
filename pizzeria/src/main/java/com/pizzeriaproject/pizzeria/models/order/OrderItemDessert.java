package com.pizzeriaproject.pizzeria.models.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pizzeriaproject.pizzeria.models.Dessert;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "order_item_dessert")
@Entity(name = "OrderItemDessert")
@Data
public class OrderItemDessert {
    @Id
    @SequenceGenerator(name = "order_item_dessert_sequence", sequenceName = "order_item_dessert_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_dessert_sequence")
    @Column(unique = true, updatable = false)
    private Long id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dessert_id")
    private Dessert dessert;

    private Integer amount;
    private Double price;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItemDessert() {
    }

    public OrderItemDessert(Long id, Dessert dessert, Integer amount, Double price, Order order) {
        this.id = id;
        this.dessert = dessert;
        this.amount = amount;
        this.price = price;
        this.order = order;
    }

    public OrderItemDessert(Dessert dessert, Integer amount, Double price, Order order) {
        this.dessert = dessert;
        this.amount = amount;
        this.price = price;
        this.order = order;
    }
}
