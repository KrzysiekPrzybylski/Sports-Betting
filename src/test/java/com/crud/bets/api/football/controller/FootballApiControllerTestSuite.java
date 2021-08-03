package com.crud.bets.api.football.controller;

import com.crud.bets.api.football.client.FootballApiClient;
import com.crud.bets.domain.dto.FootballMatchDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(FootballApiController.class)
class FootballApiControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FootballApiClient footballApiClient;

    @Test
    public void testGetMatches() throws Exception {
        //Given
        FootballMatchDto footballMatchDto1 = FootballMatchDto.builder()
                .country("England")
                .league("Premier league")
                .teamOneName("Liverpool")
                .teamTwoName("Manchester City")
                .teamOneScore("1")
                .teamTwoScore("2")
                .date(LocalDate.of(2011, 11,4))
                .build();
        FootballMatchDto footballMatchDto2 = FootballMatchDto.builder()
                .country("England")
                .league("Premier league")
                .teamOneName("Arsenal")
                .teamTwoName("Chelsea")
                .teamOneScore("2")
                .teamTwoScore("4")
                .date(LocalDate.of(2014, 1,14))
                .build();
        List<FootballMatchDto> matches = new ArrayList<>();
        matches.add(footballMatchDto1);
        matches.add(footballMatchDto2);

        when(footballApiClient.getLastMatches(13,30)).thenReturn(matches);

        //When&Then
        mockMvc.perform(get("/matches/league/13/days/30").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].country_name", is("England")))
                .andExpect(jsonPath("$[0].league_name", is("Premier league")))
                .andExpect(jsonPath("$[0].match_hometeam_name", is("Liverpool")))
                .andExpect(jsonPath("$[0].match_awayteam_name", is("Manchester City")))
                .andExpect(jsonPath("$[0].match_hometeam_score", is("1")))
                .andExpect(jsonPath("$[0].match_awayteam_score", is("2")))
                .andExpect(jsonPath("$[0].match_date", is("2011-11-04")))
                .andExpect(jsonPath("$[1].country_name", is("England")))
                .andExpect(jsonPath("$[1].league_name", is("Premier league")))
                .andExpect(jsonPath("$[1].match_hometeam_name", is("Arsenal")))
                .andExpect(jsonPath("$[1].match_awayteam_name", is("Chelsea")))
                .andExpect(jsonPath("$[1].match_hometeam_score", is("2")))
                .andExpect(jsonPath("$[1].match_awayteam_score", is("4")))
                .andExpect(jsonPath("$[1].match_date", is("2014-01-14")));
    }
}