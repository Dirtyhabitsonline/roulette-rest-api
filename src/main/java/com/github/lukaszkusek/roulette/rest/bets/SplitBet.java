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
public class SplitBet extends BaseBet {

    @NotNull
    @RouletteNumbers({
            "1 2",
            "2 3",
            "4 5",
            "5 6",
            "7 8",
            "8 9",
            "10 11",
            "11 12",
            "13 14",
            "14 15",
            "16 17",
            "17 18",
            "19 20",
            "20 21",
            "22 23",
            "23 24",
            "25 26",
            "26 27",
            "28 29",
            "29 30",
            "31 32",
            "32 33",
            "34 35",
            "35 36",
            "1 4",
            "4 7",
            "7 10",
            "10 13",
            "13 16",
            "16 19",
            "19 22",
            "22 25",
            "25 28",
            "28 31",
            "31 34",
            "2 5",
            "5 8",
            "8 11",
            "11 14",
            "14 17",
            "17 20",
            "20 23",
            "23 26",
            "26 29",
            "29 32",
            "32 35",
            "3 6",
            "6 9",
            "9 12",
            "12 15",
            "15 18",
            "18 21",
            "21 24",
            "24 27",
            "27 30",
            "30 33",
            "33 36"
    })
    private Set<Integer> numbers;

    @Override
    protected boolean isWinningNumber(Integer number) {
        return numbers.contains(number);
    }

    @Override
    protected Integer payout() {
        return 17;
    }
}
