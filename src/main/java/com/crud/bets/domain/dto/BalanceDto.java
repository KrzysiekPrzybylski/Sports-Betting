package com.crud.bets.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class BalanceDto {
    private LocalDate rateDate;
    private BigDecimal plnBalance;
    private BigDecimal eurBalance;
    private BigDecimal usdBalance;
    private BigDecimal gbpBalance;
}
