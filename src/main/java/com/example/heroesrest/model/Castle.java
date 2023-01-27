package com.example.heroesrest.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="castles")
@Data
public class Castle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="side_id")
    private Side side;
}
