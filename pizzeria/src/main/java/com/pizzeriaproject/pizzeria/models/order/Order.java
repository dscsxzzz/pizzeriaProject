package com.pizzeriaproject.pizzeria.models.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pizzeriaproject.pizzeria.models.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Table(name = "orders")
@Entity(name = "Order")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @Column(name = "order_id", unique = true, updatable = false)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItemPizza> pizzas;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItemDessert> desserts;

    private Date date;

    public Order(Long id, User user, List<OrderItemPizza> pizzas, List<OrderItemDessert> desserts, Date date) {
        this.id = id;
        this.user = user;
        this.pizzas = pizzas;
        this.desserts = desserts;
        this.date = date;
    }

    public Order(User user, List<OrderItemPizza> pizzas, List<OrderItemDessert> desserts, Date date) {
        this.user = user;
        this.pizzas = pizzas;
        this.desserts = desserts;
        this.date = date;
    }
}
