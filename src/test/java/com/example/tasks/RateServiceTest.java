package com.example.tasks;

import com.example.tasks.client.RateClient;
import com.example.tasks.exception.NotFoundCurrencyException;
import com.example.tasks.mapper.RateMapper;
import com.example.tasks.model.*;
import com.example.tasks.repository.RateRepository;
import com.example.tasks.service.RateService;
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
    @Mock
    private RateMapper rateMapper;
    private final Rate rate = new Rate();
    private final RateDto rateDto1 = new RateDto();
    private final RateDto rateDto2 = new RateDto();
    private final ApiDto apiDto = new ApiDto();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rate.setCurrency("USD");
        rateDto1.setCode("USD");
        rateDto1.setMid(BigDecimal.valueOf(1.234));
        rateDto2.setCode("EUR");
        rateDto2.setMid(BigDecimal.valueOf(2.345));
        apiDto.setRates(Arrays.asList(rateDto1, rateDto2));
    }

    @Test
    void findAll_ReturnsListOfRates() {
        RateService rateService = new RateService(rateClient, rateRepository, rateMapper);
        List<Rate> rateList = Arrays.asList(new Rate(), new Rate());
        when(rateRepository.findAll()).thenReturn(rateList);

        List<RateOutputDto> result = rateService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void searchCurrencyValue_ExistingCurrency_ReturnsCurrencyValue() throws NotFoundCurrencyException {

        RateService rateService = new RateService(rateClient, rateRepository, rateMapper);

        apiDto.setRates(Arrays.asList(rateDto1, rateDto2));
        when(rateClient.getRate()).thenReturn(new ApiDto[]{apiDto});

        BigDecimal result = rateService.searchCurrencyValue("USD");

        assertEquals(BigDecimal.valueOf(1.234), result);
    }

    @Test
    void searchCurrencyValue_NonExistingCurrency_ThrowsNotFoundCurrencyException() {

        RateService rateService = new RateService(rateClient, rateRepository, rateMapper);
        when(rateClient.getRate()).thenReturn(new ApiDto[]{apiDto});

        assertThrows(NotFoundCurrencyException.class, () -> rateService.searchCurrencyValue("GBP"));
    }


    @Test
    void saveRate_ValidRate_ReturnsRateSelectedDto() throws NotFoundCurrencyException {

        RateService rateService = new RateService(rateClient, rateRepository, rateMapper);

        when(rateClient.getRate()).thenReturn(new ApiDto[]{apiDto});
        when(rateRepository.save(any(Rate.class))).thenReturn(rate);
        when(rateMapper.toDto(any(Rate.class))).thenReturn(new RateSelected());

        RateSelected result = rateService.saveRate(rate);

        assertNotNull(result);
        verify(rateRepository, times(1)).save(rate);
        verify(rateMapper, times(1)).toDto(rate);
    }

}
