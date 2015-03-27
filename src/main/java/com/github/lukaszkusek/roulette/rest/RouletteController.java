package com.github.lukaszkusek.roulette.rest;

import com.github.lukaszkusek.roulette.rest.bets.Bets;
import com.github.lukaszkusek.roulette.rest.results.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RouletteController {

    @Autowired
    private Roulette roulette;

    @RequestMapping(value = "/spin", method = POST)
    public Results spin(@Valid @RequestBody Bets bets) {
        return roulette.play(bets);
    }
}
