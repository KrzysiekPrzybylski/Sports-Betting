package com.crud.bets.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRatesDto {

    @JsonProperty("rates")
    private RatesDto ratesDto;
    @JsonProperty("date")
    private LocalDate date;

}
