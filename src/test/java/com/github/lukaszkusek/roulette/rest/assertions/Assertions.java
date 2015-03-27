package com.github.lukaszkusek.roulette.rest.assertions;

import com.github.lukaszkusek.roulette.rest.results.Results;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

public class Assertions extends org.assertj.core.api.Assertions {

    public static <T> ResponseEntityAssert<T> assertThat(ResponseEntity<T> actual) {
        return new ResponseEntityAssert<T>(actual);
    }

    public static HttpStatusCodeExceptionAssert assertThat(HttpStatusCodeException actual) {
        return new HttpStatusCodeExceptionAssert(actual);
    }

    public static ResultsAssert assertThat(Results actual) {
        return new ResultsAssert(actual);
    }
}
