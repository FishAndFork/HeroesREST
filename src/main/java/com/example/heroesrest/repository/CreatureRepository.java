package com.example.heroesrest.repository;

import com.example.heroesrest.model.Creature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreatureRepository extends JpaRepository<Creature, Integer> {

    List<Creature> findByNameEqualsIgnoreCase(String name);

    List<Creature> findByCastleId(int id);

    @Query(value = """
                    SELECT cr.*
                    FROM creatures cr
                    LEFT JOIN creatures_abilities c_a
                        ON cr.id = c_a.creature_id
                    LEFT JOIN abilities a
                        ON c_a.ability_id = a.id
                    JOIN castles
                        ON cr.castle_id = castles.id
                    WHERE LOWER(cr.name) LIKE CONCAT('%', :name, '%')
                        AND LOWER(castles.name) LIKE CONCAT('%', :castle, '%')
                        AND LOWER(COALESCE(a.name, '')) LIKE CONCAT('%', :ability, '%')
                    """,
            nativeQuery = true)
    List<Creature> findCreatures(@Param("name") String name,
                                 @Param("castle") String castle,
                                 @Param("ability") String ability);
}
