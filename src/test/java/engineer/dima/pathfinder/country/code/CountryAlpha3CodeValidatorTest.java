package engineer.dima.pathfinder.country.code;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CountryAlpha3CodeValidatorTest {

    private CountryAlpha3CodeValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        validator = new CountryAlpha3CodeValidator();
        context = mock(ConstraintValidatorContext.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"USA", "gbr", "fRa"})
    void testValidCountryCode(String countryCode) {
        assertTrue(validator.isValid(countryCode, context));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {" USA", "US", "826", "France", ""})
    void testInvalidCountryCode(String countryCode) {
        assertFalse(validator.isValid(countryCode, context));
    }
}
