package com.crud.bets.mapper;

import com.crud.bets.domain.ExchangeRates;
import com.crud.bets.domain.dto.ExchangeRatesDto;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRatesMapper {

    public ExchangeRates mapToExchangeRates(ExchangeRatesDto exchangeRatesDto) {
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setDate(exchangeRatesDto.getDate());
        exchangeRates.setEuroRate(exchangeRatesDto.getRatesDto().getEuroRate());
        exchangeRates.setDollarRate(exchangeRatesDto.getRatesDto().getDollarRate());
        exchangeRates.setPoundRate(exchangeRatesDto.getRatesDto().getPoundRate());

        return exchangeRates;
    }
}
