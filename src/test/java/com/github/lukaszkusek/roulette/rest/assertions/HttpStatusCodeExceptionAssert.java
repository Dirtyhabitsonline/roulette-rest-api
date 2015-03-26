package com.github.lukaszkusek.roulette.rest.assertions;

import org.assertj.core.api.AbstractAssert;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class HttpStatusCodeExceptionAssert extends AbstractAssert<HttpStatusCodeExceptionAssert, HttpStatusCodeException> {

    protected HttpStatusCodeExceptionAssert(HttpStatusCodeException actual) {
        super(actual, HttpStatusCodeExceptionAssert.class);
    }

    public HttpStatusCodeExceptionAssert hasStatus(HttpStatus expected) {
        isNotNull();

        if (!actual.getStatusCode().equals(expected)) {
            failWithMessage("Expected status code to be <%s> but was <%s>", expected, actual.getStatusCode());
        }

        return this;
    }

    public HttpStatusCodeExceptionAssert responseBodyContains(String expected) {
        isNotNull();

        String responseBody = actual.getResponseBodyAsString();

        if (!responseBody.contains(expected)) {
            failWithMessage("Expected response body to contain <%s> but was <%s>", expected, responseBody);
        }

        return this;
    }
}
