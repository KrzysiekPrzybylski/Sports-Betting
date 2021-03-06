package com.crud.bets.validator;

import com.crud.bets.domain.*;
import com.crud.bets.exception.NotValidCartSlipException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CartSlipValidatorTestSuite {

  @Test
  public void testEmptyCartSlip() {
      //Given
      CartSlipValidator cartSlipValidator = new CartSlipValidator();

      Slip slip = new Slip();
      slip.setStake(new BigDecimal(132.12));
      slip.setTotalOdds(new BigDecimal(1.92));

      User user = new User();
      user.setBalance(new BigDecimal(2000));
      user.setCartSlip(slip);
      user.setEmail("test@gmail.com");
      user.setName("test name");
      user.setLastName("test last name");

      //When&Then
      try{
          cartSlipValidator.validateCartSlip(user);
          assert false;
      } catch (NotValidCartSlipException e) {
          assert true;
      }
  }

  @Test
    public void testCartSlipWithInactiveBet() {
      //Given
      CartSlipValidator cartSlipValidator = new CartSlipValidator();

      Slip slip = new Slip();
      slip.setStake(new BigDecimal(132.12));
      slip.setTotalOdds(new BigDecimal(1.92));
      slip.setState(SlipState.UNORDERED);

      Event event = new Event();
      event.setTeamOneName("Real Madryt");
      event.setTeamTwoName("Barcelona");
      event.setDateTime(LocalDateTime.of(2019, 12, 12, 20, 30, 0));

      Bet betOne = new Bet();
      betOne.setActive(false);
      betOne.setEvent(event);
      betOne.setType(BetType.ONE);
      betOne.setOdds(new BigDecimal("1.91"));
      slip.getBets().add(betOne);

      User user = new  User();
      user.setBalance(new BigDecimal("2000"));
      user.setCartSlip(slip);
      user.setEmail("mail@gmail.com");
      user.setName("test name");
      user.setLastName("testLastName");

      //When&Then
      try {
          cartSlipValidator.validateCartSlip(user);
          assert false;
      } catch (NotValidCartSlipException e) {
          assert true;
      }
  }
    @Test
    public void testCartSlipWithBetsFromTheSameEvent() {
        //Given
        CartSlipValidator validator = new CartSlipValidator();

        Slip slip = new Slip();
        slip.setStake(new BigDecimal("132.12"));
        slip.setTotalOdds(new BigDecimal("1.92"));
        slip.setState(SlipState.UNORDERED);

        Event event = new Event();
        event.setTeamOneName("Real Madryt");
        event.setTeamTwoName("Barcelona");
        event.setDateTime(LocalDateTime.of(2019, 12, 12, 20, 30, 0));

        Bet betOne = new Bet();
        betOne.setActive(true);
        betOne.setEvent(event);
        betOne.setType(BetType.ONE);
        betOne.setOdds(new BigDecimal("1.91"));
        slip.getBets().add(betOne);

        Bet betTwo = new Bet();
        betTwo.setActive(true);
        betTwo.setEvent(event);
        betTwo.setType(BetType.TWO);
        betTwo.setOdds(new BigDecimal("2.42"));
        slip.getBets().add(betTwo);

        User user = new  User();
        user.setBalance(new BigDecimal("2000"));
        user.setCartSlip(slip);
        user.setEmail("mail@gmail.com");
        user.setName("test name");
        user.setLastName("testLastName");

        //When&Then
        try {
            validator.validateCartSlip(user);
            assert false;
        } catch (NotValidCartSlipException e) {
            assert true;
        }
    }
    @Test
    public void testTooExpensiveCartSlip() {
        //Given
        CartSlipValidator validator = new CartSlipValidator();

        Slip slip = new Slip();
        slip.setStake(new BigDecimal("2000.01"));
        slip.setTotalOdds(new BigDecimal("1.12"));

        Event event = new Event();
        event.setTeamOneName("Real Madryt");
        event.setTeamTwoName("Barcelona");
        event.setDateTime(LocalDateTime.of(2019, 12, 12, 20, 30, 0));

        Bet betOne = new Bet();
        betOne.setActive(true);
        betOne.setEvent(event);
        betOne.setType(BetType.ONE);
        betOne.setOdds(new BigDecimal("1.91"));
        slip.getBets().add(betOne);

        User user = new  User();
        user.setBalance(new BigDecimal("2000"));
        user.setCartSlip(slip);
        user.setEmail("mail@gmail.com");
        user.setName("test name");
        user.setLastName("testLastName");

        //When&Then
        try {
            validator.validateCartSlip(user);
            assert false;
        } catch (NotValidCartSlipException e) {
            assert true;
        }
    }

}