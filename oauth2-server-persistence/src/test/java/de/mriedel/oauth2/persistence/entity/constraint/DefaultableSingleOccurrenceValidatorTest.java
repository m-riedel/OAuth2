package de.mriedel.oauth2.persistence.entity.constraint;

import de.mriedel.oauth2.persistence.commons.Defaultable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
public class DefaultableSingleOccurrenceValidatorTest {

    private DefaultableSingleOccurrenceValidator validator;

    @BeforeEach
    void init(){
        validator = new DefaultableSingleOccurrenceValidator();
        validator.initialize(null);
    }

    @Test
    void shouldReturnFalseForNull(){
        boolean valid = validator.isValid(null, null);
        assertFalse(valid);
    }

    @Test
    void shouldReturnFalseIfNoElementsArePresent(){
        List<Defaultable> defaultables = List.of();
        boolean valid = validator.isValid(defaultables, null);
        assertFalse(valid);
    }

    @Test
    void shouldReturnFalseIfMoreThanOneElementIsDefault(){
        List<Defaultable> defaultables = List.of(
                new TestDefaultable(true),
                new TestDefaultable(true)
        );
        boolean valid = validator.isValid(defaultables, null);
        assertFalse(valid);
    }

    @Test
    void shouldReturnTrueIfExactlyOneElementIsDefault(){
        List<Defaultable> defaultables = List.of(
                new TestDefaultable(false),
                new TestDefaultable(true)
        );
        boolean valid = validator.isValid(defaultables, null);
        assertTrue(valid);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class TestDefaultable implements Defaultable{
        private boolean isDefault;
        @Override
        public boolean isDefault() {
            return isDefault;
        }
    }
}
