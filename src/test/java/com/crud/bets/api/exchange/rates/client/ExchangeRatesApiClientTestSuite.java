package com.crud.bets.api.exchange.rates.client;

import com.crud.bets.api.exchange.rates.config.ExchangeRatesApiConfig;
import com.crud.bets.domain.dto.ExchangeRatesDto;
import com.crud.bets.domain.dto.RatesDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ExchangeRatesApiClientTestSuite {

    @InjectMocks
    private ExchangeRatesApiClient apiClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ExchangeRatesApiConfig apiConfig;

    @Test
    public void testGetExchangeRates() throws URISyntaxException {
        //Given
        when(apiConfig.getExchangeRateApiEndpoint()).thenReturn("http://test.com");
        when(apiConfig.getExchangeRateApiBase()).thenReturn("test");
        URI url = new URI("http://test.com?base=test");

        RatesDto ratesDto = new RatesDto();
        ratesDto.setDollarRate(new BigDecimal("3.4"));
        ratesDto.setEuroRate(new BigDecimal("4.2"));
        ratesDto.setPoundRate(new BigDecimal("4.5"));
        ExchangeRatesDto exchangeRatesDto = new ExchangeRatesDto();
        exchangeRatesDto.setDate(LocalDate.now());
        exchangeRatesDto.setRatesDto(ratesDto);

        when(restTemplate.getForObject(url, ExchangeRatesDto.class)).thenReturn(exchangeRatesDto);

        //When
        ExchangeRatesDto retrievedExchangeRatesDto = apiClient.getExchangeRates();

        //Then
        assertEquals(new BigDecimal("3.4"), retrievedExchangeRatesDto.getRatesDto().getDollarRate());
        assertEquals(new BigDecimal("4.2"), retrievedExchangeRatesDto.getRatesDto().getEuroRate());
        assertEquals(new BigDecimal("4.5"), retrievedExchangeRatesDto.getRatesDto().getPoundRate());
        assertEquals(LocalDate.now(), retrievedExchangeRatesDto.getDate());
    }
}