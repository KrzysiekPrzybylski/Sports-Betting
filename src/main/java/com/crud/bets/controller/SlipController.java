package com.crud.bets.controller;

import com.crud.bets.domain.dto.SlipDto;
import com.crud.bets.exception.SlipNotFoundException;
import com.crud.bets.exception.UserNotFoundException;
import com.crud.bets.mapper.SlipMapper;
import com.crud.bets.service.SlipService;
import com.crud.bets.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/{users")
public class SlipController {

    private final SlipMapper slipMapper;
    private final UserService userService;
    private final SlipService slipService;
    private final OrderSlipFacade orderSlipFacade;
    @GetMapping("/{userId}/slips")
    public Set<SlipDto> getSlips(@PathVariable long userId) throws UserNotFoundException {
        return slipMapper.mapToSlipDtoSet(userService.getUser(userId).getSlips());
    }
    @GetMapping("/{userId}/cart")
    public SlipDto getCartSlip(@PathVariable long userId) throws UserNotFoundException {
        return slipMapper.mapToSlipDto(userService.getUser(userId).getCartSlip());
    }
    @PutMapping("/{userId}/cart/empty")
    public SlipDto emptySlip(@PathVariable long userId) throws UserNotFoundException, SlipNotFoundException {
        return slipMapper.mapToSlipDto(slipService.emptyCartSlip(userService.getUser(userId).getCartSlip().getSlipId()));
    }
    @PatchMapping
    public SlipDto addBetToSlip(@PathVariable long userId, @PathVariable long betId) {
        return slipMapper.mapToSlipDto(slipService.remo)
    }
}