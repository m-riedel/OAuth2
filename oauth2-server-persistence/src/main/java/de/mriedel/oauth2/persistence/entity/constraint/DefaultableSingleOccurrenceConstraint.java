package de.mriedel.oauth2.persistence.entity.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DefaultableSingleOccurrenceValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultableSingleOccurrenceConstraint {
    String message() default "Only one defaultable object is allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
