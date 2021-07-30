package com.crud.bets.domain.detail;

import com.crud.bets.domain.Slip;
import com.sun.istack.NotNull;
import lombok.*;
import org.apache.el.lang.ELArithmetic;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class SLipOrderDetails {

    @NotNull
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private LocalDateTime orderDateTime;
    @NotNull
    private BigDecimal stake;
    @NotNull
    private BigDecimal odds;
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SLIP_ID")
    private Slip slip;
}
