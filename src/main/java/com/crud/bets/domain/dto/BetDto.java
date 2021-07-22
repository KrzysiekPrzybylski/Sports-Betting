package com.crud.bets.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BetDto {

    private long betId;
    private String event;
    private String type;
    private BigDecimal odds;
    private boolean active;

}
