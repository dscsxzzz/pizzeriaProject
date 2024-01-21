package com.pizzeriaproject.pizzeria.services;

import com.pizzeriaproject.pizzeria.models.order.*;
import com.pizzeriaproject.pizzeria.repository.OrderItemDessertRepository;
import com.pizzeriaproject.pizzeria.repository.OrderItemPizzaRepository;
import com.pizzeriaproject.pizzeria.repository.OrderRepository;
import com.pizzeriaproject.pizzeria.repository.UserRepository;
import com.pizzeriaproject.pizzeria.utils.GlobalExceptionHandler;
import com.pizzeriaproject.pizzeria.utils.OrderItemMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemPizzaRepository orderItemPizzaRepository;

    private final OrderItemDessertRepository orderItemDessertRepository;

    private final UserRepository userRepository;

    private final GlobalExceptionHandler globalExceptionHandler;

    public OrderService(OrderRepository orderRepository, OrderItemPizzaRepository orderItemPizzaRepository, OrderItemDessertRepository orderItemDessertRepository, UserRepository userRepository, GlobalExceptionHandler globalExceptionHandler) {
        this.orderRepository = orderRepository;
        this.orderItemPizzaRepository = orderItemPizzaRepository;
        this.orderItemDessertRepository = orderItemDessertRepository;
        this.userRepository = userRepository;
        this.globalExceptionHandler = globalExceptionHandler;
    }

    @Transactional
    public ResponseEntity<?> addOrder(Long id, List<OrderItemPizzaDTO> pizzaDTOList, List<OrderItemDessertDTO> dessertDTOList) {
        try {
            Order order = new Order();
            order.setUser(userRepository.findById(id).get());
            order.setDate(new java.sql.Date(new Date().getTime()));

            List<OrderItemPizza> orderItemPizzaPizzaList = OrderItemMapper.convertToEntityList(pizzaDTOList);
            List<OrderItemDessert> orderItemDessertList = OrderItemMapper.convertToEntityDessertList(dessertDTOList);

            Order savedOrder = orderRepository.save(order);
            for (OrderItemPizza item : orderItemPizzaPizzaList) {
                item.setOrder(savedOrder);
                orderItemPizzaRepository.save(item);
            }
            for (OrderItemDessert item : orderItemDessertList) {
                item.setOrder(savedOrder);
                orderItemDessertRepository.save(item);
            }
            return new ResponseEntity<>("Saved", HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            return globalExceptionHandler.handleAllExceptions(ex);
        }
    }

    public ResponseEntity<?> getOrder(Long id) {
        try {
            List<Order> orderList = orderRepository.findAllByUserId(id);
            return new ResponseEntity<>(orderList, HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            return globalExceptionHandler.handleAllExceptions(ex);
        }
    }
}
