package com.example.heroesrest.validation;

import com.example.heroesrest.dto.UpdateAbilitiesDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UpdateAbilitiesValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UpdateAbilitiesDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpdateAbilitiesDTO dto = (UpdateAbilitiesDTO) target;

        if (dto.getCreatureId() == null && dto.getCreatureName() == null) {
            errors.rejectValue("creatureId", "", "You must provide an id or name of the creature");
        }
    }
}
