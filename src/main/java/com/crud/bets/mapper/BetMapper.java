package com.crud.bets.mapper;

import com.crud.bets.domain.Bet;
import com.crud.bets.domain.dto.BetDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetMapper {


    public Bet mapToBet (final BetDto betDto) {
        return  new Bet(
                betDto.getBetId(),
                betDto.getEvent(),
                betDto.getType(),
                betDto.getOdds(),
                betDto.isActive()
        );
    }
    public BetDto mapToBetDto(final Bet bet) {

        return BetDto.builder()
                .betId(bet.getBetId())
                .event(bet.getEvent())
                .type(bet.getType())
                .odds(bet.getOdds())
                .active(bet.isActive())
                .build();
    }
    public  List<BetDto> mapToBetDtoList (List<Bet> bets) {
        return bets.stream()
                .map(this::mapToBetDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
