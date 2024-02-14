package com.pizzeriaproject.pizzeria.repository;

import com.pizzeriaproject.pizzeria.models.Dessert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DessertRepository extends JpaRepository<Dessert, Long> {
    List<Dessert> findAll();
}
