package com.crud.bets.domain;

import com.crud.bets.repository.BetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BetTestSuite {
    @Autowired
    private BetRepository betRepository;
    @Test
    public void BetTestSave(){
        //Given
        Bet bet = new Bet("noga", "Euro",new BigDecimal(2),true );
        //When
        betRepository.save(bet);
        long id = bet.getBetId();
        Optional<Bet> betList = betRepository.findById(id);

        //Then
        assertTrue(betList.isPresent());
        //CleanUp
        //betRepository.deleteById(id);

    }

}