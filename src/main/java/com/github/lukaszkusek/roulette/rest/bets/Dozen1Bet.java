package com.github.lukaszkusek.roulette.rest.bets;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

import static com.google.common.collect.ImmutableSet.of;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Dozen1Bet extends BaseBet {

    public static final Set<Integer> DOZEN_1_NUMBERS = of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

    @Override
    protected boolean isWinningNumber(Integer number) {
        return DOZEN_1_NUMBERS.contains(number);
    }

    @Override
    protected Integer payout() {
        return 2;
    }
}
