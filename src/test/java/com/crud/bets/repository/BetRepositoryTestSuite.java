package com.crud.bets.repository;

import com.crud.bets.domain.Bet;
import com.crud.bets.domain.BetType;
import com.crud.bets.domain.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BetRepositoryTestSuite {

    @Autowired
    private BetRepository betRepository;
    @Autowired
    private EventRepository eventRepository;

    @Test

    public void testSaveAllAndFindAll() {
//        //Given
//        Event event = new Event();
//
//        Bet betOne = new Bet();
//        betOne.setEvent(event);
//        betOne.setType(BetType.ONE);
//        betOne.setOdds(new BigDecimal(1.91));
//
//        Bet betTwo = new Bet();
//        betTwo.setEvent(event);
//        betTwo.setType(BetType.TWO);
//        betTwo.setOdds(new BigDecimal(3.41));
//
//        Bet betZero = new Bet();
//        betZero.setEvent(event);
//        betZero.setType(BetType.ZERO);
//        betZero.setOdds(new BigDecimal(3.41));
//
//        //When
//        betRepository.save(betOne);
//        betRepository.save(betTwo);
//        betRepository.save(betZero);
//        List<Bet> bets = betRepository.findAll();
//        int numberOfBets = bets.size();
//
//        //Then
//        assertEquals(3, numberOfBets);
    }
    @Test
    public void testDelete() {
//        //Given
//        Event event = new Event();
//
//        int initialNumberOfBets = betRepository.findAll().size();
//        Bet betZero = new Bet();
//        betZero.setEvent(event);
//        betZero.setType(BetType.TWO);
//        betZero.setOdds(new BigDecimal("3.41"));
//        betRepository.save(betZero);
//
//        //When
//        betRepository.delete(betZero);
//        List<Bet> bets = betRepository.findAll();
//        int numberOfBets = bets.size();
//
//        //Then
//        assertEquals(initialNumberOfBets, numberOfBets);
//        assertFalse(bets.contains(betZero));
//    }
    }
}