package com.github.lukaszkusek.roulette.rest.bets;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OddBet extends BaseBet {

    @Override
    protected boolean isWinningNumber(Integer number) {
        return number % 2 == 1;
    }

    @Override
    protected Integer payout() {
        return 1;
    }
}
