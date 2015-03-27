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
public class RedBet extends BaseBet {

    public static final Set<Integer> RED_NUMBERS = of(
            1, 3, 5, 7, 9, 12,
            14, 16, 18, 19, 21, 23,
            25, 27, 30, 32, 34, 36);

    @Override
    protected boolean isWinningNumber(Integer number) {
        return RED_NUMBERS.contains(number);
    }

    @Override
    protected Integer payout() {
        return 1;
    }
}
