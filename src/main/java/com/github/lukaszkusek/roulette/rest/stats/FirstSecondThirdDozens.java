package com.github.lukaszkusek.roulette.rest.stats;

import com.github.lukaszkusek.roulette.rest.bets.Dozen1Bet;
import com.github.lukaszkusek.roulette.rest.bets.Dozen2Bet;
import com.github.lukaszkusek.roulette.rest.bets.Dozen3Bet;

class FirstSecondThirdDozens extends BasePercentageDistribution<FirstSecondThirdDozens.FirstSecondThirdDozen> {

    @Override
    public void add(Integer winningNumber) {
        if (winningNumber != 0) {
            super.add(winningNumber);
        }
    }

    @Override
    protected FirstSecondThirdDozen fromNumber(Integer winningNumber) {
        return FirstSecondThirdDozen.fromNumber(winningNumber);
    }

    @Override
    protected FirstSecondThirdDozen[] definedValues() {
        return FirstSecondThirdDozen.values();
    }

    protected enum FirstSecondThirdDozen {
        FIRST, SECOND, THIRD;

        public static FirstSecondThirdDozen fromNumber(Integer number) {
            if (Dozen1Bet.DOZEN_1_NUMBERS.contains(number)) {
                return FIRST;
            }

            if (Dozen2Bet.DOZEN_2_NUMBERS.contains(number)) {
                return SECOND;
            }

            if (Dozen3Bet.DOZEN_3_NUMBERS.contains(number)) {
                return THIRD;
            }

            throw new IllegalArgumentException(number + " is not in 1st, 2nd or 3rd dozen.");
        }
    }
}
