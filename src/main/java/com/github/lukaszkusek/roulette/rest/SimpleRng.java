package com.github.lukaszkusek.roulette.rest;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SimpleRng implements Rng {

    private Random random = new Random();

    @Override
    public int get(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}
