package com.github.lukaszkusek.roulette.rest;

import com.github.lukaszkusek.roulette.rest.bets.Bets;
import com.github.lukaszkusek.roulette.rest.bets.CornerBet;
import com.github.lukaszkusek.roulette.rest.bets.RedBet;
import com.github.lukaszkusek.roulette.rest.bets.StraightBet;
import com.github.lukaszkusek.roulette.rest.results.Results;
import org.assertj.core.api.ThrowableAssert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Set;
import java.util.stream.IntStream;

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

    @Before
    public void setupRng() {
        given(rng.get(0, 36)).willReturn(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 1, 1, 1, 2, 2, 2, 3, 4, 9, 36);
    }

    @Test
    public void shouldReturnMethodNotAllowedForAnyOtherMethodThanPOST() {
        shouldReturnMethodNotAllowedFor(this::GET);
        shouldReturnMethodNotAllowedFor(this::PUT);
        shouldReturnMethodNotAllowedFor(this::DELETE);
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

    @Test
    public void shouldHaveCorrectWinningNumbers() {
        // given
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

        Bets bets = new Bets();
        // TODO more bets
        bets.setRedBet(redBet(1));
        bets.setStraightBets(asList(straightBet(1, 1), straightBet(2, 1)));
        bets.setCornerBets(singletonList(cornerBet(of(1, 2, 4, 5), 1)));

        // when
        ResponseEntity<Results> response = POST(bets, Results.class);

        // then
        assertThat(response.getBody())
                // TODO more bets
                .hasRedBet(redBet(2))
                .hasStraightBets(straightBet(1, 36), straightBet(2, 0))
                .hasCornerBets(cornerBet(of(1, 2, 4, 5), 9));
    }

    @Test
    public void shouldProvideProperStatistics() {
        // given
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

    private CornerBet cornerBet(Set<Integer> numbers, int amount) {
        CornerBet cornerBet = new CornerBet();
        cornerBet.setNumbers(numbers);
        cornerBet.setAmount(amount);
        return cornerBet;
    }

    private RedBet redBet(int amount) {
        RedBet redBet = new RedBet();
        redBet.setAmount(amount);
        return redBet;
    }

    private StraightBet straightBet(Integer number, Integer amount) {
        StraightBet straightBet = new StraightBet();
        straightBet.setNumber(number);
        straightBet.setAmount(amount);
        return straightBet;
    }

    private void shouldReturnMethodNotAllowedFor(ThrowableAssert.ThrowingCallable methodCall) {
        // when
        HttpStatusCodeException exception = catchHttpStatusCodeException(methodCall);

        // then
        assertThat(exception).hasStatus(METHOD_NOT_ALLOWED);
    }

}
