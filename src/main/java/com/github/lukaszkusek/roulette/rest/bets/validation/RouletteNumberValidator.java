package com.github.lukaszkusek.roulette.rest.bets.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RouletteNumberValidator implements ConstraintValidator<RouletteNumber, Integer> {

    @Override
    public void initialize(RouletteNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return IsRouletteNumber.get().test(value);
    }
}
