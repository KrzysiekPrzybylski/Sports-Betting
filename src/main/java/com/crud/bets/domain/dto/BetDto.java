package com.crud.bets.domain.dto;

import com.crud.bets.domain.BetType;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BetDto {

    private long betId;
    private long eventId;
    private BetType type;
    private BigDecimal odds;
    private boolean active;

}
