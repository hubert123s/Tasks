package com.example.tasks.service;

import com.example.tasks.client.RateClient;
import com.example.tasks.exception.NotFoundCurrencyException;
import com.example.tasks.model.*;
import com.example.tasks.repository.RateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class RateServiceTest {
    @Mock
    private RateClient rateClient;
    @Mock
    private RateRepository rateRepository;
    private final Rate rate = new Rate();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rate.setCurrency("USD");
    }

    @Test
    void findAll_ReturnsListOfRates() {
        RateService rateService = new RateService(rateClient, rateRepository);
        List<Rate> rateList = Arrays.asList(new Rate(), new Rate());
        when(rateRepository.findAll()).thenReturn(rateList);

        List<RateRequestResponse> result = rateService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void searchCurrencyValue_ExistingCurrency_ReturnsCurrencyValue() throws NotFoundCurrencyException {

        RateService rateService = new RateService(rateClient, rateRepository);

        when(rateClient.getRate()).thenReturn(new RatesResponse[]{createRatesResponse()});

        BigDecimal result = rateService.searchCurrencyValue("USD");

        assertEquals(BigDecimal.valueOf(1.234), result);
    }

    @Test
    void searchCurrencyValue_NonExistingCurrency_ThrowsNotFoundCurrencyException() {

        RateService rateService = new RateService(rateClient, rateRepository);
        when(rateClient.getRate()).thenReturn(new RatesResponse[]{createRatesResponse()});

        assertThrows(NotFoundCurrencyException.class, () -> rateService.searchCurrencyValue("GBP"));
    }


    @Test
    void saveRate() throws NotFoundCurrencyException {

        RateService rateService = new RateService(rateClient, rateRepository);

        when(rateClient.getRate()).thenReturn(new RatesResponse[]{createRatesResponse()});
        when(rateRepository.save(any(Rate.class))).thenReturn(rate);

        GetCurrentCurrencyValueCommandResponse result = rateService.getCurrentCurrencyValue(createGetCurrentCurrencyValueCommand());

        assertNotNull(result);
        verify(rateRepository, times(1)).save(any(Rate.class));
    }

    private RateResponse createRateResponse() {
        return new RateResponse("USD", BigDecimal.valueOf(1.234));
    }

    private RatesResponse createRatesResponse() {
        return new RatesResponse(Arrays.asList(createRateResponse()));
    }

    private GetCurrentCurrencyValueCommand createGetCurrentCurrencyValueCommand() {
        return new GetCurrentCurrencyValueCommand("USD", "Jan Nowak");
    }
}
