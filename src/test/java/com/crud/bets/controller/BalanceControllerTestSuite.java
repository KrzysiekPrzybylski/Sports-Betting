package com.crud.bets.controller;

import com.crud.bets.domain.dto.BalanceDto;
import com.crud.bets.service.BalanceService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@ExtendWith(SpringExtension.class)
@WebMvcTest(BalanceController.class)
@AutoConfigureMockMvc
class BalanceControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
//
//    @MockBean
//    private BalanceService balanceService;
//
//    @Test
//    public void testGetBalance() throws Exception {
//        //Given
//        BalanceDto balanceDto = BalanceDto.builder()
//                .rateDate(LocalDate.of(2019,6,11))
//                .eurBalance(new BigDecimal("1000"))
//                .usdBalance(new BigDecimal("2000"))
//                .gbpBalance(new BigDecimal("3000"))
//                .plnBalance(new BigDecimal("4000"))
//                .build();
//        when(balanceService.getUserBalance(23)).thenReturn(balanceDto);
//
//        //When&Then
//        mockMvc
//                .perform(MockMvcRequestBuilders.get("/users/23/balance")
//                            .contentType(MediaType.APPLICATION_JSON))
//                            //.andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.plnBalance", Matchers.is(4000)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.gbpBalance", Matchers.is(3000)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.usdBalance", Matchers.is(2000)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.eurBalance", Matchers.is(1000)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.rateDate", Matchers.is("2019-06-11")));
//    }
}