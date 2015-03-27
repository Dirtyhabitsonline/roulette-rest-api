package com.github.lukaszkusek.roulette.rest.bets;

import com.github.lukaszkusek.roulette.rest.bets.validation.RouletteNumber;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StraightBet extends BaseBet {

    @NotNull
    @RouletteNumber
    private Integer number;

    @Override
    protected boolean isWinningNumber(Integer number) {
        return this.number.equals(number);
    }

    @Override
    protected Integer payout() {
        return 35;
    }
}
