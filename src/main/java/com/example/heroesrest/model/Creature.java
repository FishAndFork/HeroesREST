package com.example.heroesrest.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;


@Entity
@Table(name="creatures")
@Data
public class Creature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="level")
    private Integer level;

    @ManyToOne
    @JoinColumn(name="castle_id")
    private Castle castle;

    @Column(name="damage_min")
    private Integer damageMin;

    @Column(name="damage_max")
    private Integer damageMax;

    @Column(name="attack")
    private Integer attack;

    @Column(name="defense")
    private Integer defense;

    @Column(name="health")
    private Integer health;

    @Column(name="speed")
    private Integer speed;

    @Column(name="growth")
    private Integer growth;

    @Column(name="gold")
    private Integer gold;

    @ManyToOne
    @JoinColumn(name="resource_id")
    private Resource resource;

    @Column(name="resource_amount")
    private Integer resourceAmount;

    @ManyToMany(fetch = FetchType.LAZY,
                cascade=CascadeType.ALL)
    @JoinTable(
            name = "creatures_abilities",
            joinColumns = @JoinColumn(name = "creature_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    @ToString.Exclude // avoid stackoverflow caused by @Data which generates hashCode and toString with cross-dependent fields
    @EqualsAndHashCode.Exclude
    private List<Ability> abilities;

    @Column(name="ai_value")
    private Integer aiValue;

}
