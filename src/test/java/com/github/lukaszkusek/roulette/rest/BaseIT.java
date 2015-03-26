package com.github.lukaszkusek.roulette.rest;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Roulette.class)
@WebIntegrationTest({"server.port=0", "management.port=0"})
public abstract class BaseIT {

    @Value("${local.server.port}")
    private int port;
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        restTemplate = new TestRestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
    }

    protected <T> ResponseEntity<Object> POST(T requestBody) {
        return restTemplate.postForEntity(spinUrl(), requestBody, Object.class);
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
