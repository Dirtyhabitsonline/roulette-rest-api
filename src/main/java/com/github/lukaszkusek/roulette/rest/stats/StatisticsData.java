package com.github.lukaszkusek.roulette.rest.stats;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import static java.util.stream.IntStream.rangeClosed;

class StatisticsData {

    private Queue<Integer> history = new CircularFifoQueue<>(Statistics.MAX_SIZE);
    private Map<Integer, Integer> absentFor = new HashMap<>();

    public StatisticsData() {
        initAbsentForWithZeros();
    }

    public void add(Integer winningNumber) {
        history.add(winningNumber);
        absentFor.put(winningNumber, 0);
        increaseAbsentForCounters();
    }

    private void initAbsentForWithZeros() {
        rangeClosed(0, 36).forEach(number -> absentFor.put(number, 0));
    }

    private void increaseAbsentForCounters() {
        rangeClosed(0, 36).forEach(number -> absentFor.compute(number, (key, value) -> value + 1));
    }

    public Queue<Integer> getHistory() {
        return history;
    }

    public Map<Integer, Integer> getAbsentFor() {
        return absentFor;
    }
}
