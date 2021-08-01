package com.crud.bets.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
    @Column(precision = 5, scale = 3)
    @Min(value = 0)
    private BigDecimal euroRate;
    @NotNull
    @Min(value = 0)
    @Column(precision = 5, scale = 3)
    private BigDecimal dollarRate;
    @NotNull
    @Min(value = 0)
    @Column(precision = 5, scale = 3)
    private BigDecimal poundRate;
}
