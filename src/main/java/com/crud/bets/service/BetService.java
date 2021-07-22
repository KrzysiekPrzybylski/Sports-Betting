package com.crud.bets.service;

import com.crud.bets.domain.Bet;
import com.crud.bets.exception.BetNotFoundException;
import com.crud.bets.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetService {

    private final BetRepository betRepository;

    @Autowired
    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }
    public List<Bet> getBets() {
        return betRepository.findAll();
    }
    public Bet getBet(long betId) throws BetNotFoundException {
        return betRepository.findById(betId).orElseThrow(BetNotFoundException::new);
    }
    public Bet addBet(Bet bet) {
        return betRepository.save(bet);
    }
    public void deleteBet(long betId) {
        betRepository.deleteById(betId);
    }
}
