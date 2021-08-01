package com.crud.bets.service;

import com.crud.bets.domain.ExchangeRates;
import com.crud.bets.domain.User;
import com.crud.bets.domain.dto.BalanceDto;
import com.crud.bets.exception.ExchangeRatesNotAvailableException;
import com.crud.bets.exception.ExchangeRatesNotFoundException;
import com.crud.bets.exception.UserNotFoundException;
import com.crud.bets.repository.UserRepository;
import org.junit.Before;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;


import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceTestSuite {

    @InjectMocks
    private BalanceService balanceService;

    @Mock
    private ExchangeRatesService ratesService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;




    @Before
    public void init() {
        User user = new User();
        user.setBalance(new BigDecimal("6000"));
        user.setEmail("mail@gmail.com");
        user.setName("test name");
        user.setLastName("testLastName");
        when(userService.getUser(anyLong())).thenReturn(user);
    }

    @Test
    public void testGetUserBalance() throws ExchangeRatesNotFoundException, UserNotFoundException {
        //Given
        ExchangeRates rates = new ExchangeRates();
        rates.setPoundRate(new BigDecimal("3"));
        rates.setEuroRate(new BigDecimal("2"));
        rates.setDollarRate(new BigDecimal("1.5"));
        rates.setDate(LocalDate.now());
        when(ratesService.getLastRates()).thenReturn(rates);

        //When

        BalanceDto balanceDto = balanceService.getUserBalance(12);

        //Then

        assertTrue(balanceDto.getRateDate().equals(LocalDate.now()));
    }
//    @Test
//    public void testGetUserBalanceWithoutExchangeRates() throws ExchangeRatesNotFoundException {
//        //Given
//        ExchangeRates rates = new ExchangeRates();
//        rates.setPoundRate(new BigDecimal("3"));
//        rates.setEuroRate(new BigDecimal("2"));
//        rates.setDollarRate(new BigDecimal("1.5"));
//        rates.setDate(LocalDate.now());
//        when(ratesService.getLastRates()).thenThrow(new ExchangeRatesNotAvailableException());
//
//        //When
//        BalanceDto balanceDto = balanceService.getUserBalance(123);
//
//        //Then
//        assertTrue(balanceDto.getPlnBalance().compareTo(new BigDecimal("6000")) == 0);
//        assertNull(balanceDto.getEurBalance());
//        assertNull(balanceDto.getGbpBalance());
//        assertNull(balanceDto.getRateDate());
//        assertNull(balanceDto.getUsdBalance());
//    }
}