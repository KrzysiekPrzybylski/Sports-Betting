package com.crud.bets.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesDto {

    @JsonProperty("EUR")
    private BigDecimal euroRate;
    @JsonProperty("USD")
    private BigDecimal dollarRate;
    @JsonProperty("GBP")
    private BigDecimal poundRate;
}
