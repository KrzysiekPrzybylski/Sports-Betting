package com.crud.bets.controller;

import com.crud.bets.domain.dto.ValueDto;
import com.crud.bets.exception.ExchangeRatesNotFoundException;
import com.crud.bets.exception.UserNotFoundException;
import com.crud.bets.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class BalanceController {

    private final BalanceService balanceService;
    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }
    @GetMapping("/{userId}/balance")
    public ResponseEntity getGetBalance(@PathVariable long userId) throws UserNotFoundException{
        return ResponseEntity.ok(balanceService.getUserBalance(userId));
    }
    @PatchMapping()
    public ResponseEntity makePayment(@PathVariable long userId, @RequestBody ValueDto valueDto) throws UserNotFoundException {
        balanceService.payment(userId, valueDto.getValue());
        return  ResponseEntity.noContent().build();
    }

}
