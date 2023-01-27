package com.example.heroesrest.repository;

import com.example.heroesrest.model.Castle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CastleRepository extends JpaRepository<Castle, Integer> {

    List<Castle> findByNameContainingIgnoreCase(String name);
}
