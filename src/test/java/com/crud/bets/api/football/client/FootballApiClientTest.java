package com.crud.bets.api.football.client;

import com.crud.bets.api.football.config.FootballApiConfig;
import com.crud.bets.domain.dto.FootballMatchDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FootballApiClientTest {

    @InjectMocks
    private FootballApiClient footballApiClient;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private FootballApiConfig footballApiConfig;
    @BeforeEach
    void init() {
        when(footballApiConfig.getFootballApiEndpoint()).thenReturn("http://test.com");
        when(footballApiConfig.getFootballApiKey()).thenReturn("mytestkey");
    }

    @Test
    public void testLastMatches() throws URISyntaxException {
        //Given
        URI url = new URI("http://test.com?action=get_events&APIkey=mytestkey&league_id=14&from="
                + LocalDate.now().minusDays(7) + "&to=" + LocalDate.now());

        FootballMatchDto matchDto = FootballMatchDto.builder()
                .country("Test country")
                .teamOneScore("13")
                .date(LocalDate.of(2001, 12,1))
                .build();
        FootballMatchDto matchDto1 = FootballMatchDto.builder()
                .league("Test Liga")
                .build();
        FootballMatchDto[] matches = {matchDto, matchDto1};

        when(restTemplate.getForObject(url, FootballMatchDto[].class)).thenReturn(matches);

        //When
        List<FootballMatchDto> retrievedMatches = footballApiClient.getLastMatches(14, 7);
        //Then
        assertEquals(2, retrievedMatches.size());
        assertEquals("Test country", retrievedMatches.get(0).getCountry());
        assertEquals("13", retrievedMatches.get(0).getTeamOneScore());
        assertEquals("Test Liga", retrievedMatches.get(1).getLeague());
        assertEquals(LocalDate.of(2001,12,1), retrievedMatches.get(0).getDate());
    }
    @Test
    public void testLastMatchesEmpty() throws URISyntaxException {
        //Given
        URI url = new URI("http://test.com?action=get_events&APIkey=mytestkey&league_id=14&from=" + LocalDate.now().minusDays(7) + "&to=" + LocalDate.now());

        FootballMatchDto[] matchDtos = new FootballMatchDto[0];
        when(restTemplate.getForObject(url, FootballMatchDto[].class)).thenReturn(matchDtos);
        //When
        List<FootballMatchDto> retrievedMatches = footballApiClient.getLastMatches(14,7);
        //Then
        assertEquals(0, retrievedMatches.size());
    }
}