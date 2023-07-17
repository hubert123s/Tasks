package com.example.tasks.client;

import com.example.tasks.model.RatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@RequiredArgsConstructor
public class RateClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final static String IMAGE_URL = "https://api.nbp.pl/api/exchangerates/tables/A?format=json";

    public RatesResponse[] getRate() {
        ResponseEntity<RatesResponse[]> exchange = restTemplate.exchange(IMAGE_URL,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                RatesResponse[].class);
        return exchange.getBody();
    }
}

