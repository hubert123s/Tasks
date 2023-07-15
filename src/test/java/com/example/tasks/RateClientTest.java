package com.example.tasks;
import com.example.tasks.client.RateClient;
import com.example.tasks.model.ApiDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RateClientTest {
    @Mock
    private RestTemplate restTemplate;

    private RateClient rateClient;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rateClient = new RateClient();
    }

    @Test
    void getRate_ShouldReturnNonEmptyArray() {
        // Arrange
        String imageUrl = "https://api.nbp.pl/api/exchangerates/tables/A?format=json";
        ApiDto[] apiDtoArray = new ApiDto[]{new ApiDto(), new ApiDto()};
        ResponseEntity<ApiDto[]> responseEntity = ResponseEntity.ok(apiDtoArray);
        when(restTemplate.exchange(eq(imageUrl), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), eq(ApiDto[].class)))
                .thenReturn(responseEntity);

        // Act
        ApiDto[] result = rateClient.getRate();

        // Assert
        assertNotNull(result);
        assertTrue(result.length > 0);
        verify(restTemplate, times(1)).exchange(eq(imageUrl), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), eq(ApiDto[].class));
    }
    @Test
    void getRate_ShouldReturnApiDtoArray() {
        // Arrange
        String imageUrl = "https://api.nbp.pl/api/exchangerates/tables/A?format=json";
        ApiDto[] expectedApiDtoArray = new ApiDto[]{new ApiDto(), new ApiDto()};
        ResponseEntity<ApiDto[]> responseEntity = ResponseEntity.ok(expectedApiDtoArray);
        when(restTemplate.exchange(eq(imageUrl), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), eq(ApiDto[].class)))
                .thenReturn(responseEntity);
        //rateClient = new RateClient(restTemplate);
        rateClient = new RateClient();

        // Act
        ApiDto[] result = rateClient.getRate();

        // Assert
        assertArrayEquals(expectedApiDtoArray, result);
        verify(restTemplate, times(1)).exchange(eq(imageUrl), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), eq(ApiDto[].class));
    }

    @Test
    void getRate_WhenRestTemplateReturnsErrorResponse_ShouldReturnNull() {
        // Arrange
        String imageUrl = "https://api.nbp.pl/api/exchangerates/tables/A?format=json";
        ResponseEntity<ApiDto[]> responseEntity = ResponseEntity.notFound().build();
        when(restTemplate.exchange(eq(imageUrl), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), eq(ApiDto[].class)))
                .thenReturn(responseEntity);
       //rateClient = new RateClient(restTemplate);
        rateClient = new RateClient();

        // Act
        ApiDto[] result = rateClient.getRate();

        // Assert
        assertNull(result);
        verify(restTemplate, times(1)).exchange(eq(imageUrl), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), eq(ApiDto[].class));
    }
}
