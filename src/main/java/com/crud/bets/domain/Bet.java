package com.crud.bets.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name = "BETS")
public class Bet {

    @Id
    @GeneratedValue
    @NotNull
    private long betId;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name="EVENT_ID")
    private Event event;
    @NotNull
    private BetType type;
    @NotNull
    private BigDecimal odds;
    @NotNull
    private boolean active = true;
    @NotNull
    private BetResult result = BetResult.NOT_FINISHED;

    public Bet(Event event, BetType type, BigDecimal odds, boolean active) {
        this.event = event;
        this.type = type;
        this.odds = odds;
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bet)) return false;

        Bet bet = (Bet) o;

        if (betId != bet.betId) return false;
        if (active != bet.active) return false;
        if (!type.equals(bet.type)) return false;
        return odds.equals(bet.odds);
    }

    @Override
    public int hashCode() {
        int result = (int) (betId ^ (betId >>> 32));
        result = 31 * result + type.hashCode();
        result = 31 * result + odds.hashCode();
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    public void settle(Bet bet) {
        if(event.isFinished()) {
            active = false;
            switch (type) {
                case ONE:
                    result = (event.getTeamOneScore().compareTo(event.getTeamTwoScore())>0) ? BetResult.WINNING : BetResult.LOST;
                    break;
                case TWO:
                    result = (event.getTeamOneScore().compareTo(event.getTeamTwoScore())<0) ? BetResult.WINNING : BetResult.LOST;
                    break;
                case ZERO:
                    result = (event.getTeamOneScore().compareTo(event.getTeamTwoScore())==0) ? BetResult.WINNING : BetResult.LOST;
                    break;
                case ZERO_ONE:
                    result = (event.getTeamOneScore().compareTo(event.getTeamTwoScore())>=0) ? BetResult.WINNING : BetResult.LOST;
                    break;
                case ZERO_TWO:
                    result = (event.getTeamOneScore().compareTo(event.getTeamTwoScore())<=0) ? BetResult.WINNING : BetResult.LOST;
                    break;
            }
        }
    }
}
