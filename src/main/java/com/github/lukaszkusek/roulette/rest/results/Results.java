package com.github.lukaszkusek.roulette.rest.results;

import com.github.lukaszkusek.roulette.rest.bets.Bets;
import com.github.lukaszkusek.roulette.rest.bets.validation.RouletteNumber;
import com.github.lukaszkusek.roulette.rest.stats.StatisticsSummary;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class Results {

    @NotNull
    @RouletteNumber
    private Integer winningNumber;

    @Valid
    @NotNull
    private Bets bets;

    @Valid
    @NotNull
    private StatisticsSummary statistics;
}
