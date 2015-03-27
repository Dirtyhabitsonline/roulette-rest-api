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
public class Column1Bet extends BaseBet {

    private static final Set<Integer> COLUMN_1_NUMBERS = of(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34);

    @Override
    protected boolean isWinningNumber(Integer number) {
        return COLUMN_1_NUMBERS.contains(number);
    }

    @Override
    protected Integer payout() {
        return 2;
    }
}
