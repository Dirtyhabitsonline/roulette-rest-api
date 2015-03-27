package com.github.lukaszkusek.roulette.rest.assertions;

import com.github.lukaszkusek.roulette.rest.bets.CornerBet;
import com.github.lukaszkusek.roulette.rest.bets.RedBet;
import com.github.lukaszkusek.roulette.rest.bets.StraightBet;
import com.github.lukaszkusek.roulette.rest.results.Results;
import com.google.common.collect.ImmutableList;
import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class ResultsAssert extends AbstractAssert<ResultsAssert, Results> {

    protected ResultsAssert(Results actual) {
        super(actual, ResultsAssert.class);
    }

    public ResultsAssert hasWinningNumber(Integer expected) {
        isNotNull();

        if (!actual.getWinningNumber().equals(expected)) {
            failWithMessage("Expected winning number to be <%s> but was <%s>", expected, actual.getWinningNumber());
        }

        return this;
    }

    public ResultsAssert hasRedBet(RedBet expected) {
        isNotNull();

        assertThat(actual.getBets().getRedBet()).isEqualTo(expected);

        return this;
    }

    public ResultsAssert hasStraightBets(StraightBet... expected) {
        isNotNull();

        assertThat(actual.getBets().getStraightBets()).containsOnly(expected);

        return this;
    }

    public ResultsAssert hasCornerBets(CornerBet... expected) {
        isNotNull();

        assertThat(actual.getBets().getCornerBets()).containsOnly(expected);

        return this;
    }

    public ResultsAssert hasColdNumbers(Integer... numbers) {
        isNotNull();
        assertThat(actual.getStatistics().getColdNumbers()).containsExactlyElementsOf(ImmutableList.copyOf(numbers));
        return this;
    }

    public ResultsAssert hasHotNumbers(Integer... numbers) {
        isNotNull();
        assertThat(actual.getStatistics().getHotNumbers()).containsExactlyElementsOf(ImmutableList.copyOf(numbers));
        return this;
    }

    public ResultsAssert hasHistory(Integer... numbers) {
        isNotNull();
        assertThat(actual.getStatistics().getHistory()).containsExactlyElementsOf(ImmutableList.copyOf(numbers));
        return this;
    }

    public ResultsAssert hasBlackRedZeros(Integer black, Integer red, Integer zero) {
        isNotNull();
        assertThat(actual.getStatistics().getBlackRedZeros())
                .contains(entry("BLACK", black), entry("RED", red), entry("ZERO", zero));
        return this;
    }

    public ResultsAssert hasFirstSecondThirdDozens(Integer firstDozen, Integer secondDozen, Integer thirdDozen) {
        isNotNull();
        assertThat(actual.getStatistics().getFirstSecondThirdDozens())
                .contains(
                        entry("FIRST", firstDozen),
                        entry("SECOND", secondDozen),
                        entry("THIRD", thirdDozen));
        return this;
    }
}
