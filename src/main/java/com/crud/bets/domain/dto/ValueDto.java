package com.crud.bets.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ValueDto {

    @JsonProperty("value")
    private BigDecimal value;
}
