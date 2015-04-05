package com.github.lukaszkusek.roulette.rest;

import com.github.lukaszkusek.roulette.rest.bets.*;

import java.util.Set;

public class BetFactory {

    private static <T extends BaseBet> T withAmount(T bet, Integer amount) {
        bet.setAmount(amount);
        return bet;
    }

    static RedBet redBet(Integer amount) {
        return withAmount(new RedBet(), amount);
    }

    static BlackBet blackBet(Integer amount) {
        return withAmount(new BlackBet(), amount);
    }

    static Column1Bet column1Bet(Integer amount) {
        return withAmount(new Column1Bet(), amount);
    }

    static Column2Bet column2Bet(Integer amount) {
        return withAmount(new Column2Bet(), amount);
    }

    static Column3Bet column3Bet(Integer amount) {
        return withAmount(new Column3Bet(), amount);
    }

    static Dozen1Bet dozen1Bet(Integer amount) {
        return withAmount(new Dozen1Bet(), amount);
    }

    static Dozen2Bet dozen2Bet(Integer amount) {
        return withAmount(new Dozen2Bet(), amount);
    }

    static Dozen3Bet dozen3Bet(Integer amount) {
        return withAmount(new Dozen3Bet(), amount);
    }

    static EvenBet evenBet(Integer amount) {
        return withAmount(new EvenBet(), amount);
    }

    static OddBet oddBet(Integer amount) {
        return withAmount(new OddBet(), amount);
    }

    static Half1Bet half1Bet(Integer amount) {
        return withAmount(new Half1Bet(), amount);
    }

    static Half2Bet half2Bet(Integer amount) {
        return withAmount(new Half2Bet(), amount);
    }

    static CornerBet cornerBet(Set<Integer> numbers, Integer amount) {
        CornerBet cornerBet = new CornerBet();
        cornerBet.setNumbers(numbers);
        return withAmount(cornerBet, amount);
    }

    static SplitBet splitBet(Set<Integer> numbers, Integer amount) {
        SplitBet splitBet = new SplitBet();
        splitBet.setNumbers(numbers);
        return withAmount(splitBet, amount);
    }

    static StreetBet streetBet(Set<Integer> numbers, Integer amount) {
        StreetBet streetBet = new StreetBet();
        streetBet.setNumbers(numbers);
        return withAmount(streetBet, amount);
    }

    static StraightBet straightBet(Integer number, Integer amount) {
        StraightBet straightBet = new StraightBet();
        straightBet.setNumber(number);
        return withAmount(straightBet, amount);
    }
}
