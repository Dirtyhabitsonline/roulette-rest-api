package com.github.lukaszkusek.roulette.rest.bets;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class BaseBet implements Bet {

    @NotNull
    @Min(1)
    private Integer amount;

    @Override
    public void calculateOutcome(Integer number) {
        if (isWinningNumber(number)) {
            amount += payout() * amount;
        } else {
            amount = 0;
        }
    }

    protected abstract boolean isWinningNumber(Integer number);

    protected abstract Integer payout();
}
