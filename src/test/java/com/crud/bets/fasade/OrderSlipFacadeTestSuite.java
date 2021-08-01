package com.crud.bets.fasade;

import com.crud.bets.domain.Role;
import com.crud.bets.domain.Slip;
import com.crud.bets.domain.SlipState;
import com.crud.bets.domain.User;
import com.crud.bets.exception.UserNotFoundException;
import com.crud.bets.repository.UserRepository;
import com.crud.bets.validator.CartSlipValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderSlipFacadeTestSuite {

    @InjectMocks
    private OrderSlipFacade orderSlipFacade;

    @Mock
    private CartSlipValidator validator;
    @Mock
    private UserRepository userRepository;
    @Test
    public void testOrderValidSLip() throws UserNotFoundException {
        //Given
        User user = new User();
        user.setName("Test Name1");
        user.setLastName("Test Lastname1");
        user.setEmail("email1@test.com");
        user.setBalance(new BigDecimal("1231.11"));
        user.setEncryptedPassword("Password23");
        Role role = new Role();
        role.setRole("USER");
        user.getRoles().add(role);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        //When
        Slip retrievedSlip = orderSlipFacade.orderSlip(31);
        //Then
        assertEquals(SlipState.ORDERED, retrievedSlip.getState());
        verify(validator, times(1)).validateCartSlip(any(User.class));
    }
}