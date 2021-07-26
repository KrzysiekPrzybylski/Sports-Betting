package com.crud.bets.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "EVENTS")
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @NotNull
    @GeneratedValue
    private long eventId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
    @NotNull
    private LocalDateTime dateTime;
    @NotNull
    private boolean finished = true;

    @NotNull
    private String teamOneName;
    @NotNull
    private String teamTwoName;
    @NotNull
    private BigDecimal teamOneScore;
    @NotNull
    private BigDecimal teamTwoScore;


}
