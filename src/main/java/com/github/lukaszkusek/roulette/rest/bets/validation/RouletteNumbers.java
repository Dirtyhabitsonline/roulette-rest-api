package com.github.lukaszkusek.roulette.rest.bets.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RouletteNumbersValidator.class)
public @interface RouletteNumbers {

    String message() default "{com.github.lukaszkusek.roulette.rest.bets.validation.RouletteNumbers.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value();
}
