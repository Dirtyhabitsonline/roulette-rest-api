package com.github.lukaszkusek.roulette.rest;

import com.github.lukaszkusek.roulette.rest.bets.Bets;
import com.github.lukaszkusek.roulette.rest.results.Results;
import com.github.lukaszkusek.roulette.rest.stats.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Roulette {

    @Autowired
    private Wheel wheel;

    @Autowired
    private Statistics statistics;

    public Results play(Bets bets) {
        Integer number = wheel.spin();
        statistics.addNumber(number);
        bets.calculateOutcome(number);

        Results results = new Results();
        results.setBets(bets);
        results.setWinningNumber(number);
        results.setStatistics(statistics.getSummary());

        return results;
    }
}
