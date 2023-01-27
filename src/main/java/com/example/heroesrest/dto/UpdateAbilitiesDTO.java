package com.example.heroesrest.dto;

import lombok.Data;

import java.util.List;


@Data
public class UpdateAbilitiesDTO {

    private Integer creatureId;

    private String creatureName;

    private List<String> abilities;
}
