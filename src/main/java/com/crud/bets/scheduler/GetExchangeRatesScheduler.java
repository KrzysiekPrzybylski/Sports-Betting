package com.crud.bets.scheduler;

import com.crud.bets.api.exchange.rates.client.ExchangeRatesApiClient;
import com.crud.bets.mapper.ExchangeRatesMapper;
import com.crud.bets.service.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GetExchangeRatesScheduler {

    private final ExchangeRatesApiClient client;
    private final ExchangeRatesService service;
    private final ExchangeRatesMapper mapper;

    @Autowired
    public GetExchangeRatesScheduler(ExchangeRatesApiClient client, ExchangeRatesService service, ExchangeRatesMapper mapper) {
        this.client = client;
        this.service = service;
        this.mapper = mapper;
    }
    @Scheduled(cron = "0 0 10 * * *")
    public void saveRate() {
        service.save(mapper.mapToExchangeRates(client.getExchangeRates()));
    }

}
