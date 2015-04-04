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
    @RouletteNumbers({
            "1 2 4 5",
            "4 5 7 8",
            "7 8 10 11",
            "10 11 13 14",
            "13 14 16 17",
            "16 17 19 20",
            "19 20 22 23",
            "22 23 25 26",
            "25 26 28 29",
            "28 29 31 32",
            "31 32 34 35",
            "2 3 5 6",
            "5 6 8 9",
            "8 9 11 12",
            "11 12 14 15",
            "14 15 17 18",
            "17 18 20 21",
            "20 21 23 24",
            "23 24 26 27",
            "26 27 29 30",
            "29 30 32 33",
            "32 33 35 36"
    })
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
