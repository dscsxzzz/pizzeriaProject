package com.pizzeriaproject.pizzeria.models.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pizzeriaproject.pizzeria.models.Pizza;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "order_item_pizza")
@Entity(name = "OrderItemPizza")
@Data
public class OrderItemPizza {
    @Id
    @SequenceGenerator(name = "order_item_pizza_sequence", sequenceName = "order_item_pizza_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_pizza_sequence")
    @Column(unique = true, updatable = false)
    private Long id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    private Integer amount;
    private Double price;
    private String size;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id") // This name is the column in the OrderItem table
    private Order order;

    public OrderItemPizza() {
    }

    public OrderItemPizza(Long id, Pizza pizza, Integer amount, Double price, String size, Order order) {
        this.id = id;
        this.pizza = pizza;
        this.amount = amount;
        this.price = price;
        this.size = size;
        this.order = order;
    }

    public OrderItemPizza(Pizza pizza, Integer amount, Double price, String size, Order order) {
        this.pizza = pizza;
        this.amount = amount;
        this.price = price;
        this.size = size;
        this.order = order;
    }
}
