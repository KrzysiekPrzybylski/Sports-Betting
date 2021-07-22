package com.crud.bets.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity(name="EXCHANGE_RATES")
public class ExchangeRates {

    @Id
    @GeneratedValue
    @NotNull
    private long id;
    private LocalDate date;
    private BigDecimal euroRate;
    private BigDecimal dollarRate;
    private BigDecimal poundRate;
}
