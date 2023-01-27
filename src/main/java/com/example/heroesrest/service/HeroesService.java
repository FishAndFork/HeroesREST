package com.example.heroesrest.service;

import com.example.heroesrest.dto.CastleDTO;
import com.example.heroesrest.dto.CreatureDTO;
import com.example.heroesrest.dto.UpdateAbilitiesDTO;
import com.example.heroesrest.exceptionhandling.AmbiguousCreatureUpdateException;
import com.example.heroesrest.exceptionhandling.EmptySearchCriteriaException;
import com.example.heroesrest.exceptionhandling.NotFoundException;
import com.example.heroesrest.exceptionhandling.NoIdOrNameProvidedException;
import com.example.heroesrest.model.Ability;
import com.example.heroesrest.model.Castle;
import com.example.heroesrest.model.Creature;
import com.example.heroesrest.repository.AbilityRepository;
import com.example.heroesrest.repository.CastleRepository;
import com.example.heroesrest.repository.CreatureRepository;
import com.example.heroesrest.validation.UpdateAbilitiesValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class HeroesService {

    private final CreatureRepository creatureRepository;
    private final CastleRepository castleRepository;
    private final AbilityRepository abilityRepository;
    private final ModelMapper modelMapper;
    private final UpdateAbilitiesValidator updateAbilitiesValidator;

    @Autowired
    public HeroesService(CreatureRepository creatureRepository,
                         CastleRepository castleRepository,
                         AbilityRepository abilityRepository,
                         ModelMapper modelMapper,
                         UpdateAbilitiesValidator updateAbilitiesValidator) {
        this.creatureRepository = creatureRepository;
        this.castleRepository = castleRepository;
        this.abilityRepository = abilityRepository;
        this.modelMapper = modelMapper;
        this.updateAbilitiesValidator = updateAbilitiesValidator;
    }

    public CreatureDTO findCreatureById(Integer id) {
        if (id == null) {
            throw new NumberFormatException();
        }
        Creature creature = findCreatureEntityById(id);
        return convertToCreatureDTO(creature);
    }

    private List<CreatureDTO> findAllCreatures() {
        return creatureRepository.findAll()
                .stream().map(this::convertToCreatureDTO)
                .collect(Collectors.toList());
    }

    public List<CreatureDTO> findCreatures(String name, String castle, String ability) {
        if (name == null && castle == null && ability == null) {
            return findAllCreatures();
        }
        if (name == null) {
            name = "";
        }
        if (castle == null) {
            castle = "";
        }
        if (ability == null) {
            ability = "";
        }
        if (name.isBlank() && castle.isBlank() && ability.isBlank()) {
            throw new EmptySearchCriteriaException();
        }
        return creatureRepository
                .findCreatures(name.toLowerCase(), castle.toLowerCase(), ability.toLowerCase())
                .stream().map(this::convertToCreatureDTO)
                .collect(Collectors.toList());
    }

    public CastleDTO findCastleById(int id) {
        Castle castle = castleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Couldn't find castle with id '" + id + "' in database"));
        return convertToCastleDTO(castle);
    }

    public List<CastleDTO> findCastlesByName(String name) {
        return castleRepository.findByNameContainingIgnoreCase(name)
                .stream().map(this::convertToCastleDTO)
                .collect(Collectors.toList());
    }

    public List<CreatureDTO> findAllCreaturesByCastleId(int id) {
        return creatureRepository.findByCastleId(id)
                .stream().map(this::convertToCreatureDTO)
                .collect(Collectors.toList());
    }

    public CreatureDTO updateAbilities(UpdateAbilitiesDTO dto, BindingResult bindingResult) {
        updateAbilitiesValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new NoIdOrNameProvidedException();
        }
        Creature creature;
        if (dto.getCreatureId() != null) {
            creature = findCreatureEntityById(dto.getCreatureId());
        } else {
            creature = findCreatureEntityByExactName(dto.getCreatureName());
        }
        if (dto.getAbilities() == null || dto.getAbilities().isEmpty()) {
            creature.setAbilities(new ArrayList<>());
        } else {
            creature.setAbilities(saveAndGetAbilities(dto.getAbilities()));
        }
        return convertToCreatureDTO(creatureRepository.save(creature));
    }

    public List<Ability> saveAndGetAbilities(List<String> proposedAbilities) {
        return proposedAbilities.stream()
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isEmpty)).map(abilityName -> {
                    Ability proposedAbility = new Ability();
                    proposedAbility.setName(abilityName);
                    Optional<Ability> existingAbilityOptional = abilityRepository
                            .findByNameContainingIgnoreCase(abilityName);
                    if (existingAbilityOptional.isEmpty()) {
                        // If ability is not present in abilities table yet, save it and get id
                        // This way on creatureRepository.save(creature) Hibernate
                        //      won't try to save duplicate ability to 'abilities' table (using cascade)
                        proposedAbility.setId(abilityRepository.save(proposedAbility).getId());
                    } else {
                        proposedAbility.setId(existingAbilityOptional.get().getId());
                    }
                    return proposedAbility;
                })
                .collect(Collectors.toList());
    }

    private CreatureDTO convertToCreatureDTO(Creature creature) {
        CreatureDTO dto = modelMapper.map(creature, CreatureDTO.class);
        dto.setCastle(creature.getCastle().getName());
        if (creature.getResource() != null) {
            dto.setResource(creature.getResource().getName());
        }
        if (creature.getAbilities() != null) {
            dto.setAbilities(creature.getAbilities()
                            .stream().map(Ability::getName)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    private Creature findCreatureEntityByExactName(String name) {
        List<Creature> foundCreatures = creatureRepository.findByNameEqualsIgnoreCase(name);
        if (foundCreatures.isEmpty()) {
            throw new NotFoundException("Couldn't find creature with name '" + name + "' in database");
        }
        if (foundCreatures.size() > 1) {
            throw new AmbiguousCreatureUpdateException(name);
        }
        return foundCreatures.get(0);
    }

    private Creature findCreatureEntityById(int id) {
        return creatureRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Couldn't find creature with id '" + id + "' in database"));
    }

    private CastleDTO convertToCastleDTO(Castle castle) {
        CastleDTO dto = modelMapper.map(castle, CastleDTO.class);
        dto.setSide(castle.getSide().getName());
        return dto;
    }
}
