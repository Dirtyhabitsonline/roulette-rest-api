package com.github.lukaszkusek.roulette.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RouletteController {

    @RequestMapping(value = "/spin", method = POST)
    public String spin(@RequestBody Bets bets) {
        return null;
    }
}
