package com.crud.bets.service;

import java.math.BigDecimal;
import java.util.List;

import com.crud.bets.domain.*;
import com.crud.bets.exception.BetNotFoundException;
import com.crud.bets.exception.SlipNotFoundException;
import com.crud.bets.exception.SlipIsOrderedExcception;
import com.crud.bets.exception.UserNotFoundException;
import com.crud.bets.repository.BetRepository;
import com.crud.bets.repository.SlipRepository;
import com.crud.bets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlipService {

    private final SlipRepository slipRepository;
    private final BetRepository betRepository;
    private final UserRepository userRepository;
    @Autowired
    public SlipService(SlipRepository slipRepository, BetRepository betRepository, UserRepository userRepository) {
        this.slipRepository = slipRepository;
        this.betRepository = betRepository;
        this.userRepository = userRepository;
    }
    public List<Slip> getSlipsByState(SlipState state) {
        return slipRepository.findAllByState(state);
    }
    public Slip save(Slip slip) {
        return slipRepository.save(slip);
    }
    public Slip addBetToSlip(long slipId, long betId) throws  SlipNotFoundException, BetNotFoundException{
        Slip slip = slipRepository.findById(slipId).orElseThrow(SlipNotFoundException::new);
        Bet bet = betRepository.findById(betId).orElseThrow(BetNotFoundException::new);
        slip.getBets().add(bet);
        slip.refreshTotalOdds();
        return slipRepository.save(slip);
    }
    public Slip emptyCartSlip(long slipId) throws SlipNotFoundException{
        Slip cartSlip = slipRepository.findById(slipId).orElseThrow(SlipNotFoundException::new);
        if(cartSlip.getState().equals(SlipState.UNORDERED)){
            cartSlip.getBets().clear();
            cartSlip.refreshTotalOdds();
        } else {
            throw  new SlipIsOrderedExcception();
        }
        return slipRepository.save(cartSlip);
    }
    public Slip settleSlip(Slip slip) throws UserNotFoundException {
        slip.getBets().stream()
                .filter(bet-> bet.getResult().equals(BetResult.NOT_FINISHED))
                .forEach(bet -> bet.settle(bet));
        long lostBetsNumber = slip.getBets().stream()
                .filter(bet -> bet.getResult().equals(BetResult.LOST)).count();
        long notFinishedBetsNumber = slip.getBets().stream()
                .filter(bet -> bet.getResult().equals(BetResult.NOT_FINISHED)).count();
        if (lostBetsNumber>0) {
            slip.setState(SlipState.LOST);
        } else if (notFinishedBetsNumber ==0) {
            User user = userRepository.findBySlipContains(slip).orElseThrow(UserNotFoundException::new);
            user.addToBalance(slip.getStake().multiply(slip.getTotalOdds()));
            slip.setState(SlipState.WINNING);
        }
        return slipRepository.save(slip);
    }
    public Slip setStake (long userId, BigDecimal stake) throws UserNotFoundException{
        Slip slip = userRepository.findById(userId).orElseThrow(UserNotFoundException::new).getCartSlip();
        slip.setStake(stake);
        return slipRepository.save(slip);

    }

    public Slip removeBetFromSlip(long slipId, long betId) throws BetNotFoundException {
        Slip slip = slipRepository.findById(slipId).orElseThrow(SlipIsOrderedExcception::new);
        Bet bet = betRepository.findById(betId).orElseThrow(BetNotFoundException::new);
        slip.getBets().remove(bet);
        slip.refreshTotalOdds();
        return slipRepository.save(slip);
    }
}
