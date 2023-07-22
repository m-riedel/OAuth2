package de.mriedel.oauth2.persistence.entity.constraint;


import de.mriedel.oauth2.persistence.commons.Defaultable;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;

public class DefaultableSingleOccurrenceValidator implements ConstraintValidator<DefaultableSingleOccurrenceConstraint, Collection<? extends Defaultable>> {
    @Override
    public void initialize(DefaultableSingleOccurrenceConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Collection<? extends Defaultable> defaultables, ConstraintValidatorContext constraintValidatorContext) {
        if (defaultables == null || defaultables.isEmpty()) {
            return true;
        }
        int count = 0;
        for(Defaultable defaultable : defaultables) {
            if (defaultable.isDefault()) {
                count++;
            }
            if(count > 1) {
                return false;
            }
        }
        return true;
    }
}
