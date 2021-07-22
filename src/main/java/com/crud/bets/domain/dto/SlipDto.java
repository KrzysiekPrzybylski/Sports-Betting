package com.crud.bets.domain.dto;

import com.crud.bets.domain.Bet;
import com.crud.bets.domain.SlipState;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class SlipDto {

    private long slipId;
    private Set<BetDto> bets;
    private BigDecimal stake;
    private SlipState state;
    private BigDecimal totalOdds;


}
