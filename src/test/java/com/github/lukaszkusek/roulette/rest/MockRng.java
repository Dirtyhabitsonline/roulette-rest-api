package com.github.lukaszkusek.roulette.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
public class MockRng {

    @Primary
    @Bean
    public Rng registerRngMock() {
        return mock(Rng.class);
    }
}
