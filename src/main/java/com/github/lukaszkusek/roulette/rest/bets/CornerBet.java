package com.github.lukaszkusek.roulette.rest.bets;

import com.github.lukaszkusek.roulette.rest.bets.validation.RouletteNumbers;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CornerBet extends BaseBet {

    @NotNull
    @RouletteNumbers({"1 2 4 5"})
    private Set<Integer> numbers;


    @Override
    protected boolean isWinningNumber(Integer number) {
        return numbers.contains(number);
    }

    @Override
    protected Integer payout() {
        return 8;
    }
}
