package com.crud.bets.domain.dto;

import com.crud.bets.domain.Bet;
import com.crud.bets.domain.SlipState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public class SlipDto {
    @Getter(AccessLevel.NONE)
    private Set<BetDto> bets;
    private BigDecimal stake;
    private SlipState state;
    private BigDecimal totalOdds;


}
