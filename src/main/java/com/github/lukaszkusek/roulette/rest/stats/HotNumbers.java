package com.github.lukaszkusek.roulette.rest.stats;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class HotNumbers {

    private StatisticsData statisticsData;

    public HotNumbers(StatisticsData statisticsData) {
        this.statisticsData = statisticsData;
    }

    public List<Integer> get() {
        return statisticsData.getHistory().stream()
                .collect(groupingBy(identity(), counting()))
                .entrySet().stream()
                .sorted(byLongValue().reversed())
                .map(Map.Entry::getKey)
                .limit(5)
                .collect(toList());
    }

    private Comparator<Map.Entry<? extends Number, Long>> byLongValue() {
        return comparing(Map.Entry::getValue);
    }

}
