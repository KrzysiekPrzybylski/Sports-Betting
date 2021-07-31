package com.crud.bets.api.exchange.rates.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class ExchangeRatesApiConfig {
    @Value("${api.exchange.rates.endpoint}")
    public String exchangeRateApiEndpoint;

    @Value("${api.exchange.rates.base}")
    private String exchangeRateApiBase;

}
