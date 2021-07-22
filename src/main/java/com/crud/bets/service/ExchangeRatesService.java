package com.crud.bets.service;

import com.crud.bets.domain.ExchangeRates;
import com.crud.bets.exception.ExchangeRatesNotFoundException;
import com.crud.bets.repository.ExchangeRatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRatesService {

    private final ExchangeRatesRepository ratesRepository;

    @Autowired
    public ExchangeRatesService(ExchangeRatesRepository ratesRepository) {
        this.ratesRepository = ratesRepository;
    }
    public ExchangeRates save(ExchangeRates exchangeRates) {
        return ratesRepository.save(exchangeRates);
    }
    public ExchangeRates getLastRates() throws ExchangeRatesNotFoundException{
        return ratesRepository.getLastRates().  orElseThrow(ExchangeRatesNotFoundException::new);
    }

}
