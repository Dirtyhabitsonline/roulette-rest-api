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
public class BlackBet extends BaseBet {

    public static final Set<Integer> BLACK_NUMBERS = of(
            2, 4, 6, 8, 10, 11,
            13, 15, 17, 20, 22, 24,
            26, 28, 29, 31, 33, 35);

    @Override
    protected boolean isWinningNumber(Integer number) {
        return BLACK_NUMBERS.contains(number);
    }

    @Override
    protected Integer payout() {
        return 1;
    }
}
