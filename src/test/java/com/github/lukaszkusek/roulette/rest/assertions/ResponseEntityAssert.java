package com.github.lukaszkusek.roulette.rest.assertions;

import org.assertj.core.api.AbstractAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityAssert<T> extends AbstractAssert<ResponseEntityAssert<T>, ResponseEntity<T>> {

    protected ResponseEntityAssert(ResponseEntity<T> actual) {
        super(actual, ResponseEntityAssert.class);
    }

    public ResponseEntityAssert<T> hasStatus(HttpStatus expected) {
        isNotNull();

        if (!actual.getStatusCode().equals(expected)) {
            failWithMessage("Expected status code to be <%s> but was <%s>", expected, actual.getStatusCode());
        }

        return this;
    }
}
