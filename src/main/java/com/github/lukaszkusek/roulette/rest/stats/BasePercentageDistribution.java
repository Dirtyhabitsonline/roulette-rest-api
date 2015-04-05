package com.github.lukaszkusek.roulette.rest.stats;

import com.google.common.primitives.Ints;
import lombok.Data;
import lombok.NonNull;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.Map;
import java.util.Queue;
import java.util.function.Function;

import static java.util.Arrays.asList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

abstract class BasePercentageDistribution<T extends Enum> implements PercentageDistribution {

    private Queue<T> values = new CircularFifoQueue<>(Statistics.MAX_SIZE);

    @Override
    public void add(Integer winningNumber) {
        values.add(fromNumber(winningNumber));
    }

    protected abstract T fromNumber(Integer winningNumber);

    @Override
    public Map<String, Integer> get() {
        Map<T, Long> valuesCount = values.stream().collect(groupingBy(identity(), counting()));

        asList(definedValues()).forEach(definedValue -> valuesCount.putIfAbsent(definedValue, 0L));

        Long total = valuesCount.values().stream().reduce(0L, Long::sum);

        return valuesCount.entrySet().stream()
                .map(calculatePercentage(total))
                .collect(toMap(pair -> pair.getFirst().name(), Pair::getSecond));
    }

    protected abstract T[] definedValues();

    private Function<Map.Entry<T, Long>, Pair<T, Integer>> calculatePercentage(Long total) {
        return entry ->
                new Pair<>(
                        entry.getKey(),
                        total == 0 ? 0 : Ints.saturatedCast((entry.getValue() * 100) / total)
                );
    }

    @Data
    private static class Pair<T, R> {

        @NonNull
        private T first;

        @NonNull
        private R second;
    }
}
