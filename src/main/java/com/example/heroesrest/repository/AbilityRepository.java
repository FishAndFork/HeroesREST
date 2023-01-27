package com.example.heroesrest.repository;

import com.example.heroesrest.model.Ability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AbilityRepository extends JpaRepository<Ability, Integer> {

    Optional<Ability> findByNameContainingIgnoreCase(String name);
}
