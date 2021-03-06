package com.dzone.mancala.web.client;

import com.dzone.mancala.web.model.KalahaGame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/*
    This class performs the api invocation for the web client
 */
@Component
@Slf4j
public class MancalaClient {

    private RestTemplate restTemplate;

    private MancalaClientConfig mancalaClientConfig;

    public MancalaClient(@Autowired RestTemplate restTemplate, @Autowired MancalaClientConfig mancalaClientConfig) {
        this.restTemplate = restTemplate;
        this.mancalaClientConfig = mancalaClientConfig;
    }

    public KalahaGame startNewMancalaGame() throws Exception {

        String url = mancalaClientConfig.getNewMancalaGameUrl();

        log.info("calling:" + url);

        ResponseEntity<KalahaGame> gameResponse = this.restTemplate.postForEntity(url, null, KalahaGame.class);

        log.info("response: " + new ObjectMapper().writerWithDefaultPrettyPrinter().
                writeValueAsString(gameResponse.getBody()));

        return gameResponse.getBody();
    }

    public KalahaGame sowMancalaGame(String gameId, Integer pitIndex) throws Exception {

        String url = mancalaClientConfig.getSowMancalaGameUrl(gameId, pitIndex);

        log.info("calling: " + url);

        ResponseEntity<KalahaGame> response = restTemplate.exchange(url, HttpMethod.PUT, null, KalahaGame.class);

        log.info("response: " + new ObjectMapper().writerWithDefaultPrettyPrinter().
                writeValueAsString(response.getBody()));

        return response.getBody();
    }
}
