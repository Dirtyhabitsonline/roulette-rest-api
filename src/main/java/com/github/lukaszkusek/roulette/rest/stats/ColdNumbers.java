package com.github.lukaszkusek.roulette.rest.stats;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class ColdNumbers {

    private StatisticsData statisticsData;

    public ColdNumbers(StatisticsData statisticsData) {
        this.statisticsData = statisticsData;
    }

    public List<Integer> get() {
        return statisticsData.getAbsentFor().entrySet().stream()
                .sorted(byIntValue().reversed())
                .map(Map.Entry::getKey)
                .limit(5)
                .collect(toList());
    }

    private Comparator<Map.Entry<? extends Number, Integer>> byIntValue() {
        return comparing(Map.Entry::getValue);
    }

}
