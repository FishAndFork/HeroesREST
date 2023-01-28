package com.example.heroesrest.controller;

import com.example.heroesrest.dto.CreatureDTO;
import com.example.heroesrest.dto.UpdateAbilitiesDTO;
import com.example.heroesrest.service.HeroesService;
import com.example.heroesrest.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creatures")
public class CreaturesController {

    private final HeroesService heroesService;

    @Autowired
    public CreaturesController(HeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @GetMapping(value = "{id}")
    @JsonView(View.CreatureWithCastle.class)
    public CreatureDTO getCreatureById(@PathVariable Integer id) {

        return heroesService.findCreatureById(id);
    }

    @GetMapping(value = "")
    public List<CreatureDTO> findCreatures(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) String castle,
                                        @RequestParam(required = false) String ability) {
        return this.heroesService.findCreatures(name, castle, ability);
    }

    @GetMapping(value = "names")
    public List<String> findCreatureNames() {
        return this.heroesService.findAllNames();
    }

    @PostMapping(value="add-abilities")
    public CreatureDTO updateAbilities(@RequestBody UpdateAbilitiesDTO dto,
                                                       BindingResult bindingResult) {
        return heroesService.updateAbilities(dto, bindingResult);
    }
}
