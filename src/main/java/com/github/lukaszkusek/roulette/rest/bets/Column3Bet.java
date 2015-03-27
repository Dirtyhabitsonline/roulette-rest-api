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
public class Column3Bet extends BaseBet {

    private static final Set<Integer> COLUMN_3_NUMBERS = of(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36);

    @Override
    protected boolean isWinningNumber(Integer number) {
        return COLUMN_3_NUMBERS.contains(number);
    }

    @Override
    protected Integer payout() {
        return 2;
    }
}
