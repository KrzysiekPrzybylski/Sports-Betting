package com.crud.bets.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import java.math.BigDecimal;
import java.time.LocalDate;

@NamedNativeQuery(
        name = "ExchangeRates.getLastRates",
        query = "SELECT * FROM exchange_rates ORDER BY date DESC LIMIT 1",
        resultClass = ExchangeRates.class
)
@Data
@Entity(name="EXCHANGE_RATES")
public class ExchangeRates {

    @Id
    @GeneratedValue
    @NotNull
    private long id;
    @NotNull
    private LocalDate date;
    @NotNull
    private BigDecimal euroRate;
    @NotNull
    private BigDecimal dollarRate;
    @NotNull
    private BigDecimal poundRate;
}
