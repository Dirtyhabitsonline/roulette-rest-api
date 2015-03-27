package com.github.lukaszkusek.roulette.rest.bets.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class RouletteNumbersValidator implements ConstraintValidator<RouletteNumbers, Collection<Integer>> {

    private Set<Set<Integer>> allowedCombinations;

    @Override
    public void initialize(RouletteNumbers rouletteNumbers) {
        allowedCombinations =
                asList(rouletteNumbers.value())
                        .parallelStream()
                        .map(convertStringToListOfIntegers())
                        .collect(Collectors.toSet());
    }

    private Function<String, Set<Integer>> convertStringToListOfIntegers() {
        return numbers ->
                asList(numbers.split(" "))
                        .stream()
                        .map(Integer::valueOf)
                        .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Collection<Integer> value, ConstraintValidatorContext context) {
        return allowedCombinations.contains(value);
    }
}
