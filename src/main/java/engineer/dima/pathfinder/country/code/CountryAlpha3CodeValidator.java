package engineer.dima.pathfinder.country.code;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Locale;

public class CountryAlpha3CodeValidator implements ConstraintValidator<CountryAlpha3Code, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return value != null && Locale.getISOCountries(Locale.IsoCountryCode.PART1_ALPHA3).contains(value.toUpperCase());
    }
}
