package com.crud.bets.mapper;

import com.crud.bets.domain.Bet;
import com.crud.bets.domain.Event;
import com.crud.bets.domain.dto.BetDto;
import com.crud.bets.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BetMapper {

    private final EventService eventService;

    @Autowired
    public BetMapper(EventService eventService) {
        this.eventService = eventService;
    }


    public Bet mapToBet (final BetDto betDto) {
        Event event = eventService.getEvent(betDto.getEventId());
        return  new Bet(
                event,
                betDto.getType(),
                betDto.getOdds(),
                betDto.isActive()
        );
    }
    public BetDto mapToBetDto(final Bet bet) {

        return BetDto.builder()
                .betId(bet.getBetId())
                .eventId(bet.getEvent().getEventId())
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
