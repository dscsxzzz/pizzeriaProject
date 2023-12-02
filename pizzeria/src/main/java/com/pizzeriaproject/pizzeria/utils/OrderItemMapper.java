package com.pizzeriaproject.pizzeria.utils;

import com.pizzeriaproject.pizzeria.models.order.OrderItemDessert;
import com.pizzeriaproject.pizzeria.models.order.OrderItemDessertDTO;
import com.pizzeriaproject.pizzeria.models.order.OrderItemPizza;
import com.pizzeriaproject.pizzeria.models.order.OrderItemPizzaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static OrderItemPizza convertToEntity(OrderItemPizzaDTO orderItemPizzaDTO) {
        return modelMapper.map(orderItemPizzaDTO, OrderItemPizza.class);
    }

    public static List<OrderItemPizza> convertToEntityList(List<OrderItemPizzaDTO> orderItemPizzaDTOList) {
        return orderItemPizzaDTOList.stream()
                .map(OrderItemMapper::convertToEntity)
                .collect(Collectors.toList());
    }

    public static OrderItemDessert convertToEntityDessert(OrderItemDessertDTO orderItemDessertDTO) {
        return modelMapper.map(orderItemDessertDTO, OrderItemDessert.class);
    }

    public static List<OrderItemDessert> convertToEntityDessertList(List<OrderItemDessertDTO> orderItemDessertDTOList) {
        return orderItemDessertDTOList.stream()
                .map(OrderItemMapper::convertToEntityDessert)
                .collect(Collectors.toList());
    }
}
