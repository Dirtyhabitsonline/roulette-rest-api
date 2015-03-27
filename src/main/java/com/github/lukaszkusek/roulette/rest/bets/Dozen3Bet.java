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
public class Dozen3Bet extends BaseBet {

    public static final Set<Integer> DOZEN_3_NUMBERS = of(25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36);

    @Override
    protected boolean isWinningNumber(Integer number) {
        return DOZEN_3_NUMBERS.contains(number);
    }

    @Override
    protected Integer payout() {
        return 2;
    }
}
