package com.crud.bets.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity(name = "BETS")
@NoArgsConstructor
@AllArgsConstructor
public class Bet {

    @Id
    @GeneratedValue

    private long betId;

    private String event;

    private String type;

    private BigDecimal odds;

    private boolean active = true;

}
