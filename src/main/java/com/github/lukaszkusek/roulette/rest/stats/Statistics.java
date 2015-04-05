package com.github.lukaszkusek.roulette.rest.stats;

import org.springframework.stereotype.Component;

@Component
public class Statistics {

    public static final int MAX_SIZE = 200;

    private StatisticsData statisticsData = new StatisticsData();

    private PercentageDistribution blackRedZeros = new BlackRedZeros();
    private PercentageDistribution firstSecondThirdDozens = new FirstSecondThirdDozens();

    private HotNumbers hotNumbers = new HotNumbers(statisticsData);
    private ColdNumbers coldNumbers = new ColdNumbers(statisticsData);

    public synchronized void addNumber(Integer winningNumber) {
        statisticsData.add(winningNumber);
        blackRedZeros.add(winningNumber);
        firstSecondThirdDozens.add(winningNumber);
    }

    public synchronized StatisticsSummary getSummary() {
        StatisticsSummary statisticsSummary = new StatisticsSummary();
        statisticsSummary.setColdNumbers(coldNumbers.get());
        statisticsSummary.setHotNumbers(hotNumbers.get());
        statisticsSummary.setHistory(statisticsData.getHistory());
        statisticsSummary.setBlackRedZeros(blackRedZeros.get());
        statisticsSummary.setFirstSecondThirdDozens(firstSecondThirdDozens.get());

        return statisticsSummary;
    }
}
