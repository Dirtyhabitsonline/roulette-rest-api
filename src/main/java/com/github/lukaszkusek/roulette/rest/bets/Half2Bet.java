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
public class Half2Bet extends BaseBet {

    private static final Set<Integer> HALF_2_NUMBERS = of(
            19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36);

    @Override
    protected boolean isWinningNumber(Integer number) {
        return HALF_2_NUMBERS.contains(number);
    }

    @Override
    protected Integer payout() {
        return 1;
    }
}
