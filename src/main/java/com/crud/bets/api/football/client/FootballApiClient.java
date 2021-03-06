package com.crud.bets.api.football.client;

import com.crud.bets.api.football.config.FootballApiConfig;
import com.crud.bets.domain.dto.FootballMatchDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class FootballApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FootballApiClient.class);
    private final FootballApiConfig apiConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public FootballApiClient(FootballApiConfig apiConfig, RestTemplate restTemplate) {
        this.apiConfig = apiConfig;
        this.restTemplate = restTemplate;
    }

    public List<FootballMatchDto> getLastMatches(int leagueId, int lastDays) {

        URI url = UriComponentsBuilder.fromHttpUrl(apiConfig.getFootballApiEndpoint())
                .queryParam("action", "get_events")
                .queryParam("APIkey", apiConfig.getFootballApiKey())
                .queryParam("league_id", leagueId)
                .queryParam("from", LocalDate.now().minusDays(lastDays).toString())
                .queryParam("to", LocalDate.now().toString())
                .build().encode().toUri();

        try{
            FootballMatchDto[] matchDtos = restTemplate.getForObject(url, FootballMatchDto[].class);
            return Arrays.asList(Optional.ofNullable(matchDtos).orElse(new FootballMatchDto[0]));
        } catch (RestClientException e) {
            LOGGER.info(e.getMessage(),e);
            return new ArrayList<>();
        }
    }
}


