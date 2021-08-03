package com.crud.bets.validator;

import com.crud.bets.domain.Bet;
import com.crud.bets.domain.User;
import com.crud.bets.exception.NotValidCartSlipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@Component
public class CartSlipValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartSlipValidator.class);

    public void validateCartSlip(User user) {
        Set<Bet> bets = user.getCartSlip().getBets();
        boolean correct =
                validateUserBalance(user) &&
                        validateBetsActivity(bets) &&
                        validateUniqueOfEvents(bets) &&
                        isNotEmpty(bets);
        if (!correct) {
            throw new NotValidCartSlipException();
        }
    }

    private boolean validateUserBalance(User user) {
        boolean hasEnoughFunds = user.getBalance().compareTo(user.getCartSlip().getStake()) >=0;

        if (hasEnoughFunds) {
            LOGGER.info("User " + user.getEmail() + "has enough funds on the account.");
        } else {
            LOGGER.warn("User " + user.getEmail() + "has not enough funds on the account!");
        }
        return hasEnoughFunds;
    }

    private boolean validateUniqueOfEvents(Set<Bet> bets) {
        long betIdSet = bets.stream()
                .map(bet -> bet.getEvent())
                .distinct()
                .count();
        boolean hasUniqueEvents = betIdSet == bets.size();

        if(hasUniqueEvents){
            LOGGER.info("CArt slip has bets from different events.");
        } else {
            LOGGER.warn("Cart slip has bets from the same events!");
        }
        return hasUniqueEvents;
    }

    private boolean validateBetsActivity(Set<Bet> bets) {
        long activeBetsNumber = bets.stream()
                .filter(Bet::isActive)
                .count();

        boolean hasActiveBet = activeBetsNumber == bets.size();

        if(hasActiveBet) {
            LOGGER.info("Cart slip has only active events.");
        } else {
            LOGGER.warn("Cart slip has inactive events!");
        }
        return hasActiveBet;
    }

}
