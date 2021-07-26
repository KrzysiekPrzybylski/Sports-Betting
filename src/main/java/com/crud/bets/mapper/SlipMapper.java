package com.crud.bets.mapper;

import com.crud.bets.domain.Slip;
import com.crud.bets.domain.dto.BetDto;
import com.crud.bets.domain.dto.SlipDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public class SlipMapper {

    private final BetMapper betMapper;

    @Autowired
    public SlipMapper(BetMapper betMapper) {
        this.betMapper = betMapper;
    }
    public SlipDto mapToSlipDto(Slip slip) {
        Set<BetDto> betDtos = slip.getBets().stream()
                .map(betMapper::mapToBetDto)
                .collect(Collectors.toSet());
        return new SlipDto(betDtos, slip.getStake(), slip.getState(), slip.getTotalOdds());
    }

    public Set<SlipDto> mapToSlipDtoSet(Set<Slip> slips) {
        return slips.stream()
                .map(this::mapToSlipDto)
                .collect(Collectors.toSet());
    }
}
