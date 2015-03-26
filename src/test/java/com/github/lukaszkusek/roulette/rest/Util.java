package com.github.lukaszkusek.roulette.rest;

import org.assertj.core.api.ThrowableAssert;
import org.springframework.web.client.HttpStatusCodeException;

import static org.assertj.core.api.Assertions.catchThrowable;

public class Util {

    public static HttpStatusCodeException catchHttpStatusCodeException(ThrowableAssert.ThrowingCallable methodCall) {
        return (HttpStatusCodeException) catchThrowable(methodCall);
    }
}
