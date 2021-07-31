package com.crud.bets.domain.detail;

import com.crud.bets.domain.Slip;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
public class SlipSettleDetails {
    @NotNull
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private LocalDateTime dateTime;
    @NotNull
    private BigDecimal stake;
    @NotNull
    private BigDecimal odds;
    @NotNull
    private boolean winning;
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SLIP_ID")
    private Slip slip;
}
