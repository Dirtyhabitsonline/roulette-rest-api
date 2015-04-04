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
public class StreetBet extends BaseBet {

    @NotNull
    @RouletteNumbers({
            "1 2 3",
            "4 5 6",
            "7 8 9",
            "10 11 12",
            "13 14 15",
            "16 17 18",
            "19 20 21",
            "22 23 24",
            "25 26 27",
            "28 29 30",
            "31 32 33",
            "34 35 36"
    })
    private Set<Integer> numbers;

    @Override
    protected boolean isWinningNumber(Integer number) {
        return numbers.contains(number);
    }

    @Override
    protected Integer payout() {
        return 11;
    }
}
