package engineer.dima.pathfinder.country.code;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CountryAlpha3CodeValidator.class)
@Documented
public @interface CountryAlpha3Code {
    String message() default "Incorrect ISO 3166-1 alpha-3 code format.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
