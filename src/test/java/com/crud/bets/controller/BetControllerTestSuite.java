package com.crud.bets.controller;

import com.crud.bets.domain.Bet;
import com.crud.bets.domain.BetType;
import com.crud.bets.domain.dto.BetDto;
import com.crud.bets.mapper.BetMapper;
import com.crud.bets.service.BetService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringJUnitWebConfig
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BetController.class)
@AutoConfigureMockMvc
public class BetControllerTestSuite {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BetMapper betMapper;
    @MockBean
    private BetService betService;

    @Test
    public void testGetBets() throws Exception{
        //Given
        Bet betOne = new Bet();
        betOne.setActive(true);
        betOne.setType(BetType.ONE);
        betOne.setOdds(new BigDecimal("1.91"));

        Bet betTwo = new Bet();
        betTwo.setActive(false);
        betTwo.setType(BetType.TWO);
        betTwo.setOdds(new BigDecimal("3.41"));

        List<Bet> bets = new ArrayList<>();
        bets.add(betOne);
        bets.add(betTwo);

        BetDto betOneDto = BetDto.builder()
                .active(betOne.isActive())
                .odds(betOne.getOdds())
                .type(betOne.getType())
                .build();
        BetDto betTwoDto = BetDto.builder()
                .active(betTwo.isActive())
                .odds(betTwo.getOdds())
                .type(betTwo.getType())
                .build();
        List<BetDto> betsDto = new ArrayList<>();
        betsDto.add(betOneDto);
        betsDto.add(betTwoDto);

        when(betMapper.mapToBetDtoList(bets)).thenReturn(betsDto);
        when(betService.getBets()).thenReturn(bets);

        //When&Then
        mockMvc.perform(MockMvcRequestBuilders.get("/bets").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].active", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].odds", Matchers.is(1.91)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type", Matchers.is("ONE")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].active", Matchers.is(false)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].odds", Matchers.is(3.41)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].type", Matchers.is("TWO")));

    }

}