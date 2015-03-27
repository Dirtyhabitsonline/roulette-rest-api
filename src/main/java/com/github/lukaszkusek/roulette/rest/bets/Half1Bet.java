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
public class Half1Bet extends BaseBet {

    private static final Set<Integer> HALF_1_NUMBERS = of(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);

    @Override
    protected boolean isWinningNumber(Integer number) {
        return HALF_1_NUMBERS.contains(number);
    }

    @Override
    protected Integer payout() {
        return 1;
    }
}
