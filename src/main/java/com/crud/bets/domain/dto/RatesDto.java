package com.crud.bets.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class RatesDto {

    @JsonProperty("EUR")
    private BigDecimal euroRate;
    @JsonProperty("USD")
    private BigDecimal dollarRate;
    @JsonProperty("GBP")
    private BigDecimal poundRate;
}
