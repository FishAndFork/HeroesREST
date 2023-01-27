package com.example.heroesrest.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="sides")
@Data
public class Side {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;
}
