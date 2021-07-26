package com.crud.bets.controller;

import com.crud.bets.domain.Bet;
import com.crud.bets.mapper.BetMapper;
import com.crud.bets.domain.dto.BetDto;
import com.crud.bets.exception.BetNotFoundException;
import com.crud.bets.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bets/")
public class BetController {

    private final BetService betService;
    private final BetMapper betMapper;

    @Autowired
    public BetController(BetService betService, BetMapper betMapper) {
        this.betService = betService;
        this.betMapper = betMapper;
    }

    @GetMapping
    public List<BetDto> getBets() {
        return betMapper.mapToBetDtoList(betService.getBets());
    }

    @GetMapping("{betId}")
    public BetDto getBet(@PathVariable long betId) throws BetNotFoundException {
        return  betMapper.mapToBetDto(betService.getBet(betId));
    }
    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BetDto addBet(@RequestBody BetDto betDto) {
        return betMapper.mapToBetDto(betService.addBet(betMapper.mapToBet(betDto)));
    }
    @PatchMapping
    public BetDto changeActivity(@PathVariable long betId) throws BetNotFoundException {
        return betMapper.mapToBetDto(betService.changeActivity(betId));
    }
    @DeleteMapping("{betId}")
    public void  deleteBet(@PathVariable long betId) {
        betService.deleteBet(betId);
    }

}
