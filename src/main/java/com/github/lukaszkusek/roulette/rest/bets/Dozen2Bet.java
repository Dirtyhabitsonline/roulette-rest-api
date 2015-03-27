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
public class Dozen2Bet extends BaseBet {

    public static final Set<Integer> DOZEN_2_NUMBERS = of(13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);

    @Override
    protected boolean isWinningNumber(Integer number) {
        return DOZEN_2_NUMBERS.contains(number);
    }

    @Override
    protected Integer payout() {
        return 2;
    }
}
