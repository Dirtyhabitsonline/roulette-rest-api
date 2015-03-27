package com.github.lukaszkusek.roulette.rest.bets.validation;

import java.util.function.Predicate;

public enum IsRouletteNumber implements Predicate<Integer> {
    INSTANCE;

    public static IsRouletteNumber get() {
        return INSTANCE;
    }

    @Override
    public boolean test(Integer value) {
        return value >= 0 && value <= 36;
    }
}
