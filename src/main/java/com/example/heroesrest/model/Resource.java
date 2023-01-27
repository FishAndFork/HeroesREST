package com.example.heroesrest.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="resources")
@Data
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;
}
