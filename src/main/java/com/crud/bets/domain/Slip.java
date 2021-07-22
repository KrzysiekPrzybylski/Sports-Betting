package com.crud.bets.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Slip {
    private long slipId;
    private Set<Bet> bets = new HashSet<>();
    private BigDecimal stake = BigDecimal.TEN;

    private SlipState state = SlipState.UNORDERED;
    private BigDecimal totalOdds = BigDecimal.ONE;
}
