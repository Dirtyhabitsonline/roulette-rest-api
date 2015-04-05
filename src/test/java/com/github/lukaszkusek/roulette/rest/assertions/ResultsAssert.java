package com.github.lukaszkusek.roulette.rest.assertions;

import com.github.lukaszkusek.roulette.rest.bets.*;
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
        assertThat(actual.getWinningNumber())
                .as("winning-number")
                .isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasColdNumbers(Integer... numbers) {
        assertThat(actual.getStatistics().getColdNumbers())
                .as("cold-numbers")
                .containsExactlyElementsOf(ImmutableList.copyOf(numbers));
        return this;
    }

    public ResultsAssert hasHotNumbers(Integer... numbers) {
        assertThat(actual.getStatistics().getHotNumbers())
                .as("hot-numbers")
                .containsExactlyElementsOf(ImmutableList.copyOf(numbers));
        return this;
    }

    public ResultsAssert hasHistory(Integer... numbers) {
        assertThat(actual.getStatistics().getHistory())
                .as("history")
                .containsExactlyElementsOf(ImmutableList.copyOf(numbers));
        return this;
    }

    public ResultsAssert hasBlackRedZeros(Integer black, Integer red, Integer zero) {
        assertThat(actual.getStatistics().getBlackRedZeros())
                .as("black-red-zeros")
                .contains(entry("BLACK", black), entry("RED", red), entry("ZERO", zero));
        return this;
    }

    public ResultsAssert hasFirstSecondThirdDozens(Integer firstDozen, Integer secondDozen, Integer thirdDozen) {
        assertThat(actual.getStatistics().getFirstSecondThirdDozens())
                .as("first-second-third-dozens")
                .contains(
                        entry("FIRST", firstDozen),
                        entry("SECOND", secondDozen),
                        entry("THIRD", thirdDozen));
        return this;
    }

    public ResultsAssert hasBlackBet(BlackBet expected) {
        assertThat(actual.getBets().getBlackBet()).as("BlackBet").isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasColumn1Bet(Column1Bet expected) {
        assertThat(actual.getBets().getColumn1Bet()).as("Column1Bet").isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasColumn2Bet(Column2Bet expected) {
        assertThat(actual.getBets().getColumn2Bet()).as("Column2Bet").isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasColumn3Bet(Column3Bet expected) {
        assertThat(actual.getBets().getColumn3Bet()).as("Column3Bet").isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasDozen1Bet(Dozen1Bet expected) {
        assertThat(actual.getBets().getDozen1Bet()).as("Dozen1Bet").isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasDozen2Bet(Dozen2Bet expected) {
        assertThat(actual.getBets().getDozen2Bet()).as("Dozen2Bet").isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasDozen3Bet(Dozen3Bet expected) {
        assertThat(actual.getBets().getDozen3Bet()).as("Dozen3Bet").isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasEvenBet(EvenBet expected) {
        assertThat(actual.getBets().getEvenBet()).as("EvenBet").isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasOddBet(OddBet expected) {
        assertThat(actual.getBets().getOddBet()).as("OddBet").isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasHalf1Bet(Half1Bet expected) {
        assertThat(actual.getBets().getHalf1Bet()).as("Half1Bet").isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasHalf2Bet(Half2Bet expected) {
        assertThat(actual.getBets().getHalf2Bet()).as("Half2Bet").isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasSplitBets(SplitBet... expected) {
        assertThat(actual.getBets().getSplitBets()).as("SplitBet").containsOnly(expected);
        return this;
    }

    public ResultsAssert hasStreetBets(StreetBet... expected) {
        assertThat(actual.getBets().getStreetBets()).as("StreetBets").containsOnly(expected);
        return this;
    }

    public ResultsAssert hasRedBet(RedBet expected) {
        assertThat(actual.getBets().getRedBet()).as("RedBet").isEqualTo(expected);
        return this;
    }

    public ResultsAssert hasStraightBets(StraightBet... expected) {
        assertThat(actual.getBets().getStraightBets()).as("StraightBets").containsOnly(expected);
        return this;
    }

    public ResultsAssert hasCornerBets(CornerBet... expected) {
        assertThat(actual.getBets().getCornerBets()).as("CornerBets").containsOnly(expected);
        return this;
    }
}
