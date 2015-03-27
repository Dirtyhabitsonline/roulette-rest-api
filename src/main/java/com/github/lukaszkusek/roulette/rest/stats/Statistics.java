package com.github.lukaszkusek.roulette.rest.stats;

import com.github.lukaszkusek.roulette.rest.bets.*;
import com.google.common.primitives.Ints;
import lombok.Data;
import lombok.NonNull;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

@Component
public class Statistics {

    private static final int MAX_SIZE = 200;

    private Queue<Integer> history = new CircularFifoQueue<>(MAX_SIZE);
    private Queue<BlackRedZero> blackRedZeros = new CircularFifoQueue<>(MAX_SIZE);
    private Queue<FirstSecondThirdDozen> firstSecondThirdDozens = new CircularFifoQueue<>(MAX_SIZE);
    private Map<Integer, Integer> absentFor;

    public Statistics() {
        absentFor = new HashMap<>();
        IntStream.range(0, 37).forEach(number -> absentFor.put(number, 0));
    }

    public synchronized void addNumber(Integer winningNumber) {
        history.add(winningNumber);
        blackRedZeros.add(BlackRedZero.fromNumber(winningNumber));
        if (winningNumber != 0) {
            firstSecondThirdDozens.add(FirstSecondThirdDozen.fromNumber(winningNumber));
        }
        increaseAbsentForCounters();
        absentFor.put(winningNumber, 0);
    }

    private void increaseAbsentForCounters() {
        IntStream.range(0, 37).forEach(number -> absentFor.compute(number, (key, value) -> value + 1));
    }

    public synchronized StatisticsSummary getSummary() {
        StatisticsSummary statisticsSummary = new StatisticsSummary();
        statisticsSummary.setColdNumbers(coldNumbers());
        statisticsSummary.setHotNumbers(hotNumbers());
        statisticsSummary.setHistory(history);
        statisticsSummary.setBlackRedZeros(blackRedZeros());
        statisticsSummary.setFirstSecondThirdDozens(firstSecondThirdDozens());

        return statisticsSummary;
    }

    private List<Integer> coldNumbers() {
        return absentFor.entrySet().stream()
                .sorted(byIntValue().reversed())
                .map(Map.Entry::getKey)
                .limit(5)
                .collect(toList());
    }

    private List<Integer> hotNumbers() {
        return history.stream()
                .collect(groupingBy(identity(), counting()))
                .entrySet().stream()
                .sorted(byLongValue().reversed())
                .map(Map.Entry::getKey)
                .limit(5)
                .collect(toList());
    }

    private Map<String, Integer> blackRedZeros() {
        Map<BlackRedZero, Long> blackRedoZerosCount =
                blackRedZeros.stream()
                        .collect(groupingBy(identity(), counting()));

        blackRedoZerosCount.putIfAbsent(BlackRedZero.BLACK, 0L);
        blackRedoZerosCount.putIfAbsent(BlackRedZero.RED, 0L);
        blackRedoZerosCount.putIfAbsent(BlackRedZero.ZERO, 0L);

        Long totalBlackRedZeros = blackRedoZerosCount.values().stream().reduce(0L, Long::sum);

        return blackRedoZerosCount.entrySet().stream()
                .map(calculatePercentage(totalBlackRedZeros))
                .collect(toMap(pair -> pair.getFirst().name(), Pair::getSecond));
    }

    private Map<String, Integer> firstSecondThirdDozens() {
        Map<FirstSecondThirdDozen, Long> firstSecondThirdDozensCount =
                firstSecondThirdDozens.stream()
                        .collect(groupingBy(identity(), counting()));

        firstSecondThirdDozensCount.putIfAbsent(FirstSecondThirdDozen.FIRST, 0L);
        firstSecondThirdDozensCount.putIfAbsent(FirstSecondThirdDozen.SECOND, 0L);
        firstSecondThirdDozensCount.putIfAbsent(FirstSecondThirdDozen.THIRD, 0L);

        Long totalFirstSecondThirdDozens = firstSecondThirdDozensCount.values().stream().reduce(0L, Long::sum);

        return firstSecondThirdDozensCount.entrySet().stream()
                .map(calculatePercentage(totalFirstSecondThirdDozens))
                .collect(toMap(pair -> pair.getFirst().name(), Pair::getSecond));
    }

    private <T> Function<Map.Entry<T, Long>, Pair<T, Integer>> calculatePercentage(Long total) {
        return entry -> new Pair<>(entry.getKey(), Ints.saturatedCast((entry.getValue() * 100) / total));
    }

    private Comparator<Map.Entry<? extends Number, Integer>> byIntValue() {
        return comparing(Map.Entry::getValue);
    }

    private Comparator<Map.Entry<? extends Number, Long>> byLongValue() {
        return comparing(Map.Entry::getValue);
    }

    @Data
    public static class Pair<T, R> {

        @NonNull
        private T first;

        @NonNull
        private R second;
    }

    private enum BlackRedZero {
        BLACK, RED, ZERO;

        public static BlackRedZero fromNumber(Integer number) {
            if (0 == number) {
                return ZERO;
            }

            if (BlackBet.BLACK_NUMBERS.contains(number)) {
                return BLACK;
            }

            if (RedBet.RED_NUMBERS.contains(number)) {
                return RED;
            }

            throw new IllegalArgumentException(number + " is not Zero, Black or Red.");
        }
    }

    private enum FirstSecondThirdDozen {
        FIRST, SECOND, THIRD;

        public static FirstSecondThirdDozen fromNumber(Integer number) {
            if (Dozen1Bet.DOZEN_1_NUMBERS.contains(number)) {
                return FIRST;
            }

            if (Dozen2Bet.DOZEN_2_NUMBERS.contains(number)) {
                return SECOND;
            }

            if (Dozen3Bet.DOZEN_3_NUMBERS.contains(number)) {
                return THIRD;
            }

            throw new IllegalArgumentException(number + " is not in 1st, 2nd or 3rd dozen.");
        }
    }
}
