package com.github.lukaszkusek.roulette.rest;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import static java.util.Collections.singletonList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RouletteApplication.class)
@WebIntegrationTest({"server.port=0", "management.port=0"})
public abstract class BaseIT {

    @Value("${local.server.port}")
    private int port;
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        restTemplate = new TestRestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        restTemplate.setInterceptors(singletonList(new LoggingRequestInterceptor()));
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }

    protected <T> ResponseEntity<Object> POST(T requestBody) {
        return POST(requestBody, Object.class);
    }

    protected <T, R> ResponseEntity<R> POST(T requestBody, Class<R> clazz) {
        return restTemplate.postForEntity(spinUrl(), requestBody, clazz);
    }

    protected void GET() {
        restTemplate.getForEntity(spinUrl(), Object.class);
    }

    protected void PUT() {
        restTemplate.put(spinUrl(), "");
    }

    protected void DELETE() {
        restTemplate.delete(spinUrl());
    }

    protected void HEAD() {
        restTemplate.headForHeaders(spinUrl());
    }

    private String spinUrl() {
        return "http://localhost:" + port + "/spin";
    }
}
