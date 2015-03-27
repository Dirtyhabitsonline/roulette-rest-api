package com.github.lukaszkusek.roulette.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Wheel {

    @Autowired
    private Rng rng;

    public Integer spin() {
        return rng.get(0, 36);
    }
}
