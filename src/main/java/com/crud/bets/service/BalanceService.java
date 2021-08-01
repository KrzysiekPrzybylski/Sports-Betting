package com.crud.bets.service;

import com.crud.bets.domain.ExchangeRates;
import com.crud.bets.domain.User;
import com.crud.bets.domain.dto.BalanceDto;
import com.crud.bets.exception.ExchangeRatesNotFoundException;
import com.crud.bets.exception.UserNotFoundException;
import com.crud.bets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BalanceService {

    private final ExchangeRatesService ratesService;
    private final UserRepository userRepository;

    @Autowired
    public BalanceService(ExchangeRatesService ratesService, UserRepository userRepository) {
        this.ratesService = ratesService;
        this.userRepository = userRepository;
    }

    public BalanceDto getUserBalance(long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        BigDecimal plnBalance = user.getBalance();
        try {
            ExchangeRates rates = ratesService.getLastRates();
            return BalanceDto.builder()
                    .rateDate(rates.getDate())
                    .plnBalance(plnBalance)
                    .eurBalance(plnBalance.divide(rates.getEuroRate(),3, RoundingMode.CEILING))
                    .gbpBalance(plnBalance.divide(rates.getPoundRate(),3, RoundingMode.CEILING))
                    .usdBalance(plnBalance.divide(rates.getDollarRate(),3, RoundingMode.CEILING))
                    .build();
        } catch (Exception e) {
            return BalanceDto.builder()
                    .plnBalance(plnBalance)
                    .build();
        }
    }
    public User payment(long userId, BigDecimal value) throws UserNotFoundException{
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.addToBalance(value);
        return userRepository.save(user);

    }
}
