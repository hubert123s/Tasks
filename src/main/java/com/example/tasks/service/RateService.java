package com.example.tasks.service;

import com.example.tasks.model.*;
import com.example.tasks.repository.RateRepository;
import com.example.tasks.exception.NotFoundCurrencyException;
import com.example.tasks.client.RateClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {
    private final RateClient rateClient;
    private final RateRepository rateRepository;

    public BigDecimal searchCurrencyValue(String currencyName) throws NotFoundCurrencyException {
        return Arrays.stream(rateClient.getRate())
                .map(RatesResponse::rates)
                .flatMap(Collection::stream)
                .filter(rateResponse -> rateResponse.code().equalsIgnoreCase(currencyName))
                .map(RateResponse::mid)
                .findFirst()
                .orElseThrow(() -> new NotFoundCurrencyException("Currency %s not found".formatted(currencyName)));
    }

    private RateRequestResponse toDtoWithoutID(Rate rate) {
        return new RateRequestResponse(rate.getCurrency(), rate.getName(), rate.getDate(), rate.getValue());
    }

    public List<RateRequestResponse> findAll() {
        return rateRepository.findAll()
                .stream()
                .map(this::toDtoWithoutID)
                .toList();
    }

    public GetCurrentCurrencyValueCommandResponse getCurrentCurrencyValue(GetCurrentCurrencyValueCommand getCurrentCurrencyValueCommand) throws NotFoundCurrencyException {
        BigDecimal currencyValue = searchCurrencyValue(getCurrentCurrencyValueCommand.currency());
        saveRate(getCurrentCurrencyValueCommand, currencyValue);
        return new GetCurrentCurrencyValueCommandResponse(currencyValue);
    }

    private void saveRate(GetCurrentCurrencyValueCommand getCurrentCurrencyValueCommand, BigDecimal currencyValue) {
        Rate rate = new Rate();
        rate.setName(getCurrentCurrencyValueCommand.name());
        rate.setCurrency(getCurrentCurrencyValueCommand.currency());
        rate.setValue(currencyValue);
        rateRepository.save(rate);
    }
}
