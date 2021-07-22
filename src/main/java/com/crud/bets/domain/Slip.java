package com.crud.bets.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "SLIPS")
public class Slip {

    @NotNull
    @Id
    @GeneratedValue
    private long slipId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="SLIPS_BETS",
            joinColumns = @JoinColumn(name="SLIP_ID"),
            inverseJoinColumns = @JoinColumn(name="BETS_ID")
    )
    private Set<Bet> bets = new HashSet<>();
    private BigDecimal stake = BigDecimal.TEN;

    private SlipState state = SlipState.UNORDERED;
    private BigDecimal totalOdds = BigDecimal.ONE;

    public void refreshTotalOdds() {
        BigDecimal odds= BigDecimal.ONE;
        for(Bet bet: bets) {
            odds = odds.multiply(bet.getOdds());
        }
        totalOdds = odds;
    }
}
