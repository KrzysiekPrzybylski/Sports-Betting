package com.crud.bets.controller;

import com.crud.bets.domain.Slip;
import com.crud.bets.domain.dto.SlipDto;
import com.crud.bets.domain.dto.ValueDto;
import com.crud.bets.exception.BetNotFoundException;
import com.crud.bets.exception.SlipNotFoundException;
import com.crud.bets.exception.UserNotFoundException;
import com.crud.bets.fasade.OrderSlipFacade;
import com.crud.bets.mapper.SlipMapper;
import com.crud.bets.service.SlipService;
import com.crud.bets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/{users")
public class SlipController {

    private final SlipMapper slipMapper;
    private final UserService userService;
    private final SlipService slipService;
    private final OrderSlipFacade orderSlipFacade;
    @Autowired
    public SlipController(SlipMapper slipMapper, UserService userService, SlipService slipService, OrderSlipFacade orderSlipFacade) {
        this.slipMapper = slipMapper;
        this.userService = userService;
        this.slipService = slipService;
        this.orderSlipFacade = orderSlipFacade;
    }

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
    @PatchMapping("/{userId}/cart/bets/{betId}")
    public SlipDto addBetToSlip(@PathVariable long userId, @PathVariable long betId) throws UserNotFoundException, BetNotFoundException, SlipNotFoundException {
        return slipMapper.mapToSlipDto(slipService.addBetToSlip(userService.getUser(userId).getCartSlip().getSlipId(), betId));
    }
    @DeleteMapping("/{userId}/cart/bets/{betId}")
    public SlipDto removeBetFromSlip(@PathVariable long userId, @PathVariable long betId) throws UserNotFoundException, BetNotFoundException {
        return slipMapper.mapToSlipDto(slipService.removeBetFromSlip(userService.getUser(userId).getCartSlip().getSlipId(),betId));
    }
    @PutMapping("/{userId}/cart")
    public SlipDto orderCartSLip(@PathVariable long userId) throws UserNotFoundException {
        Slip cartSlip = orderSlipFacade.orderSlip(userId);
        return slipMapper.mapToSlipDto(cartSlip);
    }
    @PatchMapping("/{userId}/cart/stake")
    public SlipDto changeStake(@PathVariable long userId, @RequestBody ValueDto valueDto) throws UserNotFoundException {
        return slipMapper.mapToSlipDto(slipService.setStake(userId, valueDto.getValue()));
    }
}