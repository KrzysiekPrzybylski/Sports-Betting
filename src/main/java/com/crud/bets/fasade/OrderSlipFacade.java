package com.crud.bets.fasade;

import com.crud.bets.domain.Slip;
import com.crud.bets.domain.SlipState;
import com.crud.bets.domain.User;
import com.crud.bets.exception.UserNotFoundException;
import com.crud.bets.repository.UserRepository;
import com.crud.bets.validator.CartSlipValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderSlipFacade {

    private final CartSlipValidator validator;
    private final UserRepository userRepository;
    @Autowired
    public OrderSlipFacade(CartSlipValidator validator, UserRepository userRepository) {
        this.validator = validator;
        this.userRepository = userRepository;
    }
    public Slip orderSlip (long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        validator.validateCartSlip(user);
        Slip cartSlip = user.getCartSlip();
        user.addToBalance(cartSlip.getStake().negate());
        cartSlip.setState(SlipState.ORDERED);
        user.getSlips().add(cartSlip);
        user.setCartSlip(new Slip());
        userRepository.save(user);

        return cartSlip;
    }
}
