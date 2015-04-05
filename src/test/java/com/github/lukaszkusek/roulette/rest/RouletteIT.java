package com.github.lukaszkusek.roulette.rest;

import com.github.lukaszkusek.roulette.rest.bets.Bets;
import com.github.lukaszkusek.roulette.rest.bets.RedBet;
import com.github.lukaszkusek.roulette.rest.results.Results;
import cz.jirutka.spring.exhandler.messages.ValidationErrorMessage;
import org.assertj.core.api.ThrowableAssert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.IOException;
import java.util.stream.IntStream;

import static com.github.lukaszkusek.roulette.rest.BetFactory.*;
import static com.github.lukaszkusek.roulette.rest.Util.catchHttpStatusCodeException;
import static com.github.lukaszkusek.roulette.rest.assertions.Assertions.assertThat;
import static com.google.common.collect.ImmutableSet.of;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RouletteIT extends BaseIT {

    @Autowired
    private Rng rng;

    @Test
    public void shouldReturnMethodNotAllowedForAnyOtherMethodThanPOST() {
        setupRng();

        shouldReturnMethodNotAllowedFor(this::GET);
        shouldReturnMethodNotAllowedFor(this::PUT);
        shouldReturnMethodNotAllowedFor(this::DELETE);
    }

    @Test
    public void shouldRequireBetsAsPostBody() {
        // given
        setupRng();
        Bets bets = null;

        // when
        HttpStatusCodeException exception = catchHttpStatusCodeException(() -> POST(bets));

        // then
        assertThat(exception)
                .hasStatus(BAD_REQUEST)
                .responseBodyContains("The content you've send is probably malformed.");
    }

    @Test
    public void shouldValidateBets() throws IOException {
        // given
        setupRng();
        Bets bets = new Bets();
        bets.setRedBet(new RedBet());

        // when
        HttpStatusCodeException exception = catchHttpStatusCodeException(() -> POST(bets));

        // then
        assertThat(exception)
                .hasStatus(UNPROCESSABLE_ENTITY)
                .hasError(validationError("redBet.amount", null, "may not be null"));
    }

    private ValidationErrorMessage validationError(String field, Object rejectedValue, String message) {
        ValidationErrorMessage errorMessage = new ValidationErrorMessage();
        errorMessage.setTitle("Validation Failed");
        errorMessage.setStatus(UNPROCESSABLE_ENTITY);
        errorMessage.setDetail("The content you've send contains 1 validation errors.");
        errorMessage.addError(field, rejectedValue, message);
        return errorMessage;
    }

    @Test
    public void shouldHandleSpinRequestForEmptyBets() {
        // given
        setupRng();
        Bets bets = new Bets();

        // when
        ResponseEntity<?> response = POST(bets);

        // then
        assertThat(response).hasStatus(OK);
    }

    @Test
    public void shouldHaveCorrectWinningNumbers() {
        // given
        rng().willReturn(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Bets bets = new Bets();

        // when
        ResponseEntity<Results> response1 = POST(bets, Results.class);
        ResponseEntity<Results> response2 = POST(bets, Results.class);
        ResponseEntity<Results> response3 = POST(bets, Results.class);
        ResponseEntity<Results> response4 = POST(bets, Results.class);
        ResponseEntity<Results> response5 = POST(bets, Results.class);
        ResponseEntity<Results> response6 = POST(bets, Results.class);
        ResponseEntity<Results> response7 = POST(bets, Results.class);
        ResponseEntity<Results> response8 = POST(bets, Results.class);
        ResponseEntity<Results> response9 = POST(bets, Results.class);

        // then
        assertThat(response1.getBody()).hasWinningNumber(1);
        assertThat(response2.getBody()).hasWinningNumber(2);
        assertThat(response3.getBody()).hasWinningNumber(3);
        assertThat(response4.getBody()).hasWinningNumber(4);
        assertThat(response5.getBody()).hasWinningNumber(5);
        assertThat(response6.getBody()).hasWinningNumber(6);
        assertThat(response7.getBody()).hasWinningNumber(7);
        assertThat(response8.getBody()).hasWinningNumber(8);
        assertThat(response9.getBody()).hasWinningNumber(9);
    }

    @Test
    public void shouldCalculateCorrectOutcomesForBets() {
        // given
        rng().willReturn(1);

        Bets bets = new Bets();
        bets.setBlackBet(blackBet(1));
        bets.setRedBet(redBet(1));
        bets.setColumn1Bet(column1Bet(1));
        bets.setColumn2Bet(column2Bet(1));
        bets.setColumn3Bet(column3Bet(1));
        bets.setDozen1Bet(dozen1Bet(1));
        bets.setDozen2Bet(dozen2Bet(1));
        bets.setDozen3Bet(dozen3Bet(1));
        bets.setEvenBet(evenBet(1));
        bets.setOddBet(oddBet(1));
        bets.setHalf1Bet(half1Bet(1));
        bets.setHalf2Bet(half2Bet(1));
        bets.setSplitBets(asList(splitBet(of(1, 2), 1), splitBet(of(2, 3), 1)));
        bets.setStreetBets(asList(streetBet(of(1, 2, 3), 1), streetBet(of(4, 5, 6), 1)));
        bets.setStraightBets(asList(straightBet(1, 1), straightBet(2, 1)));
        bets.setCornerBets(singletonList(cornerBet(of(1, 2, 4, 5), 1)));

        // when
        ResponseEntity<Results> response = POST(bets, Results.class);

        // then
        assertThat(response.getBody())
                .hasBlackBet(blackBet(0))
                .hasRedBet(redBet(2))
                .hasColumn1Bet(column1Bet(3))
                .hasColumn2Bet(column2Bet(0))
                .hasColumn3Bet(column3Bet(0))
                .hasDozen1Bet(dozen1Bet(3))
                .hasDozen2Bet(dozen2Bet(0))
                .hasDozen3Bet(dozen3Bet(0))
                .hasEvenBet(evenBet(0))
                .hasOddBet(oddBet(2))
                .hasHalf1Bet(half1Bet(2))
                .hasHalf2Bet(half2Bet(0))
                .hasSplitBets(splitBet(of(1, 2), 18), splitBet(of(2, 3), 0))
                .hasStreetBets(streetBet(of(1, 2, 3), 12), streetBet(of(4, 5, 6), 0))
                .hasStraightBets(straightBet(1, 36), straightBet(2, 0))
                .hasCornerBets(cornerBet(of(1, 2, 4, 5), 9));
    }

    @Test
    public void shouldProvideProperStatistics() {
        // given
        rng().willReturn(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 1, 1, 1, 2, 2, 2, 3, 4, 9, 36);
        Bets bets = new Bets();

        // when
        IntStream.range(0, 20).forEach(i -> POST(bets));
        ResponseEntity<Results> response = POST(bets, Results.class);

        // then
        assertThat(response.getBody())
                .hasColdNumbers(0, 11, 12, 13, 14)
                .hasHotNumbers(1, 2, 3, 4, 9)
                .hasHistory(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 1, 1, 1, 2, 2, 2, 3, 4, 9, 36)
                .hasBlackRedZeros(42, 57, 0)
                .hasFirstSecondThirdDozens(95, 0, 4);
    }

    private void setupRng() {
        rng().willReturn(1);
    }

    private BDDMockito.BDDMyOngoingStubbing<Integer> rng() {
        return given(rng.get(0, 36));
    }

    private void shouldReturnMethodNotAllowedFor(ThrowableAssert.ThrowingCallable methodCall) {
        // when
        HttpStatusCodeException exception = catchHttpStatusCodeException(methodCall);

        // then
        assertThat(exception).hasStatus(METHOD_NOT_ALLOWED);
    }

}
