package com.example.heroesrest.repository;

import com.example.heroesrest.model.Creature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreatureRepository extends JpaRepository<Creature, Integer> {

    List<Creature> findByNameEqualsIgnoreCase(String name);

    List<Creature> findByCastleIdOrderById(int id);

    @Query("""
            FROM Creature cr
            LEFT JOIN FETCH cr.abilities a
            LEFT JOIN FETCH cr.resource res
            JOIN FETCH cr.castle ca
            ORDER BY cr.id
            """)
    List<Creature> findAllOrderById();

    @Query("""
            SELECT cr.name
            FROM Creature cr
            ORDER BY cr.id
            """)
    List<String> findAllNames();

    @Query("""
            FROM Creature cr
            LEFT JOIN FETCH cr.abilities a
            LEFT JOIN FETCH cr.resource res
            JOIN FETCH cr.castle ca
            WHERE LOWER(cr.name) LIKE CONCAT('%', :name, '%')
                AND LOWER(ca.name) LIKE CONCAT('%', :castle, '%')
                AND LOWER(COALESCE(a.name, '')) LIKE CONCAT('%', :ability, '%')
            ORDER BY cr.id
            """)
    List<Creature> findCreatures(@Param("name") String name,
                                 @Param("castle") String castle,
                                 @Param("ability") String ability);
}
