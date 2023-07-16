package com.example.tasks.service;

import com.example.tasks.model.*;
import com.example.tasks.repository.RateRepository;
import com.example.tasks.exception.NotFoundCurrencyException;
import com.example.tasks.mapper.RateMapper;
import com.example.tasks.client.RateClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RateService {
    private final RateClient rateClient;
    private final RateRepository rateRepository;
    private final RateMapper rateMapper;

    private Stream<RateDto> getRatedDtoStream() {
        return Arrays.stream(rateClient.getRate()).map(ApiDto::getRates).flatMap(Collection::stream);
    }

    public BigDecimal searchCurrencyValue(String currencyName) throws NotFoundCurrencyException {
        try {
            return getRatedDtoStream()
                    .filter(api -> api.getCode()
                            .equalsIgnoreCase(currencyName))
                    .map(rateDto -> rateDto.getMid()).findFirst().orElseThrow();
        } catch (NoSuchElementException e) {
            throw new NotFoundCurrencyException("Not found data");
        }
    }

    public List<RateOutputDto> findAll() {
        return rateRepository.findAll().stream().map(rateMapper::toDtoWithoutID).toList();
    }

    public RateSelected saveRate(Rate rate) throws NotFoundCurrencyException {
        rate.setValue(searchCurrencyValue(rate.getCurrency()));
        Rate savedRate = rateRepository.save(rate);
        return rateMapper.toDto(savedRate);
    }
}
