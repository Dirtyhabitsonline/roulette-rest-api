package com.github.lukaszkusek.roulette.rest;

import org.assertj.core.api.ThrowableAssert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import static com.github.lukaszkusek.roulette.rest.Util.catchHttpStatusCodeException;
import static com.github.lukaszkusek.roulette.rest.assertions.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;

public class RouletteIT extends BaseIT {

    @Test
    public void shouldReturnMethodNotAllowedForAnyOtherMethodThanPOST() {
        shouldReturnMethodNotAllowedFor(this::GET);
        shouldReturnMethodNotAllowedFor(this::PUT);
        shouldReturnMethodNotAllowedFor(this::DELETE);
        shouldReturnMethodNotAllowedFor(this::HEAD);
    }

    @Test
    public void shouldRequireBetsAsPostBody() {
        // given
        Bets bets = null;

        // when
        HttpStatusCodeException exception = catchHttpStatusCodeException(() -> POST(bets));

        // then
        assertThat(exception)
                .hasStatus(BAD_REQUEST)
                .responseBodyContains("Required request body content is missing");
    }

    @Test
    public void shouldHandleSpinRequestForEmptyBets() {
        // given
        Bets bets = new Bets();

        // when
        ResponseEntity<?> response = POST(bets);

        // then
        assertThat(response).hasStatus(OK);
    }

    private void shouldReturnMethodNotAllowedFor(ThrowableAssert.ThrowingCallable methodCall) {
        // when
        HttpStatusCodeException exception = catchHttpStatusCodeException(methodCall);

        // then
        assertThat(exception).hasStatus(METHOD_NOT_ALLOWED);
    }

}
