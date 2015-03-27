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
public class Column2Bet extends BaseBet {

    private static final Set<Integer> COLUMN_2_NUMBERS = of(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35);

    @Override
    protected boolean isWinningNumber(Integer number) {
        return COLUMN_2_NUMBERS.contains(number);
    }

    @Override
    protected Integer payout() {
        return 2;
    }
}
