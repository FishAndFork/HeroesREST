package com.example.heroesrest.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name="abilities")
@Data
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
                mappedBy = "abilities",
                cascade = CascadeType.ALL)
    @ToString.Exclude // avoid stackoverflow caused by @Data which generates hashCode and toString with cross-dependent fields
    @EqualsAndHashCode.Exclude
    private List<Creature> creatures;
}
