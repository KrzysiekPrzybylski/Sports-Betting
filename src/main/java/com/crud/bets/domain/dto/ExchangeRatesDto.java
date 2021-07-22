package com.crud.bets.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExchangeRatesDto {
    private long id;
    private LocalDate date;
    private BigDecimal euroRate;
    private BigDecimal dollarRate;
    private BigDecimal poundRate;
}
