package com.pizzeriaproject.pizzeria.services;

import com.pizzeriaproject.pizzeria.models.Dessert;
import com.pizzeriaproject.pizzeria.repository.DessertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DessertService {
    @Autowired
    private DessertRepository dessertRepository;

    public List<Dessert> allDesserts() {
        return dessertRepository.findAll();
    }
}
