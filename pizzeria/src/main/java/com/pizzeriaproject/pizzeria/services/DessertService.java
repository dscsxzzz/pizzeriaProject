package com.pizzeriaproject.pizzeria.services;

import com.pizzeriaproject.pizzeria.models.Dessert;
import com.pizzeriaproject.pizzeria.repository.DessertRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DessertService {

    private final DessertRepository dessertRepository;

    public DessertService(DessertRepository dessertRepository) {
        this.dessertRepository = dessertRepository;
    }

    public List<Dessert> allDesserts() {
        return dessertRepository.findAll();
    }
}
