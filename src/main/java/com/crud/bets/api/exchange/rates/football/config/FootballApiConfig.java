package com.crud.bets.api.exchange.rates.football.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class FootballApiConfig {

    @Value("${api.football.endpoint}")
    private String footballApiEndpoint;

    @Value("${api.football.key}")
    private String footballApiKey;

}
