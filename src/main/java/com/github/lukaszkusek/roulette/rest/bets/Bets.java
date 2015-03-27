package com.github.lukaszkusek.roulette.rest.bets;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

@Data
public class Bets {

    @Valid
    private BlackBet blackBet;

    @Valid
    private RedBet redBet;

    @Valid
    private OddBet oddBet;

    @Valid
    private EvenBet evenBet;

    @Valid
    private Dozen1Bet dozen1Bet;

    @Valid
    private Dozen2Bet dozen2Bet;

    @Valid
    private Dozen3Bet dozen3Bet;

    @Valid
    private Column1Bet column1Bet;

    @Valid
    private Column2Bet column2Bet;

    @Valid
    private Column3Bet column3Bet;

    @Valid
    private Half1Bet half1Bet;

    @Valid
    private Half2Bet half2Bet;

    @Valid
    private List<StraightBet> straightBets;

    @Valid
    private List<SplitBet> splitBets;

    @Valid
    private List<StreetBet> streetBets;

    @Valid
    private List<CornerBet> cornerBets;

    public void calculateOutcome(Integer number) {
        allBets().parallel().forEach(bet -> bet.calculateOutcome(number));
    }

    private Stream<Bet> allBets() {
        return Stream.concat(
                asList(blackBet, redBet, oddBet, evenBet,
                        dozen1Bet, dozen2Bet, dozen3Bet,
                        column1Bet, column2Bet, column3Bet,
                        half1Bet, half2Bet)
                        .stream()
                        .filter(bet -> bet != null),
                asList(straightBets, splitBets, streetBets, cornerBets)
                        .parallelStream()
                        .filter(bets -> bets != null)
                        .<Bet>flatMap(bets -> bets.stream()));
    }
}
