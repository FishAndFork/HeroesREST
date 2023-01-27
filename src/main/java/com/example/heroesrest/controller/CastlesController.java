package com.example.heroesrest.controller;

import com.example.heroesrest.dto.CastleDTO;
import com.example.heroesrest.dto.CreatureDTO;
import com.example.heroesrest.service.HeroesService;
import com.example.heroesrest.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/castles")
@Validated
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class CastlesController {

    private final HeroesService heroesService;

    @Autowired
    public CastlesController(HeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @GetMapping(value = "{id}")
    public CastleDTO getCastle(@PathVariable Integer id) {

        return heroesService.findCastleById(id);
    }

    @GetMapping(value="", params = "name")
    public List<CastleDTO> getCastlesByName(@RequestParam @NotBlank String name) {

        return heroesService.findCastlesByName(name);
    }

    @GetMapping(value = "{id}/creatures")
    @JsonView(View.Creature.class)
    public List<CreatureDTO> getAllCreaturesByCastle(@PathVariable Integer id) {

        return heroesService.findAllCreaturesByCastleId(id);
    }
}
