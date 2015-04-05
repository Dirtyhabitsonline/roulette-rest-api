package com.github.lukaszkusek.roulette.rest.stats;

import java.util.Map;

interface PercentageDistribution {

    void add(Integer winningNumber);

    Map<String, Integer> get();

}
