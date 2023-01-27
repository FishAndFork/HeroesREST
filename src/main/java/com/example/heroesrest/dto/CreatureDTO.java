package com.example.heroesrest.dto;

import com.example.heroesrest.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.List;

@Data
public class CreatureDTO {

    private Integer id;

    private String name;

    private Integer level;

    @JsonView(View.CreatureWithCastle.class) // so the field isn't included in results of /castles/{id]/creatures
    private String castle;

    private Integer damageMin;

    private Integer damageMax;

    private Integer attack;

    private Integer defense;

    private Integer health;

    private Integer speed;

    private Integer growth;

    private Integer gold;

    private String resource;

    private Integer resourceAmount;

    private List<String> abilities;

    private Integer aiValue;
}
