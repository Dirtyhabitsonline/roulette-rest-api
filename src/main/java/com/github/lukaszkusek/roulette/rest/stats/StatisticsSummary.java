package com.github.lukaszkusek.roulette.rest.stats;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@Data
public class StatisticsSummary {

    @NotNull
    private List<Integer> coldNumbers;

    @NotNull
    private List<Integer> hotNumbers;

    @NotNull
    private Queue<Integer> history;

    @NotNull
    private Map<String, Integer> blackRedZeros;

    @NotNull
    private Map<String, Integer> firstSecondThirdDozens;

}
