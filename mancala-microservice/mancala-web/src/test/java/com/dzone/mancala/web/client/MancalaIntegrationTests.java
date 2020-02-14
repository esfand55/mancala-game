package com.dzone.mancala.web.client;

import com.dzone.mancala.web.MancalaWebApplication;
import com.dzone.mancala.web.model.KalahaGame;
import com.dzone.mancala.web.model.KalahaPit;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.SneakyThrows;
import org.apache.http.HttpHeaders;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/*
    Using Spring Cloud Contract Wiremock for inter-service communication testing between Mancala microservices
 */

@SpringBootTest (classes = MancalaWebApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureWireMock (port = 8080)
@DirtiesContext
public class MancalaIntegrationTests {

    private final Resource mancalaCreate = new ClassPathResource("mancala-creation.json");

    private final Resource mancalaSow2 = new ClassPathResource("mancala-sow-2.json");

    @MockBean
    MancalaClientConfig mancalaClientConfig;

    @Autowired
    private MancalaClient mancalaClient;

    @SneakyThrows
    private String asJson(Resource resource) {
        return StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
    }

    @Test
    public void testManacalaCreation () throws Exception{

        Mockito.when(mancalaClientConfig.getNewMancalaGameUrl()).thenReturn("http://localhost:8080/games");

        WireMock.stubFor(WireMock.post("/games")
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withStatus(HttpStatus.OK.value())
                        .withBody(asJson(mancalaCreate))
                ));

        KalahaGame kalahaGame = mancalaClient.startNewMancalaGame();

        List<KalahaPit> kalahaPits = Arrays.asList(
                new KalahaPit(1 , 6),
                new KalahaPit(2 , 6),
                new KalahaPit(3 , 6),
                new KalahaPit(4 , 6),
                new KalahaPit(5 , 6),
                new KalahaPit(6 , 6),
                new KalahaPit(7 , 0),
                new KalahaPit(8 , 6),
                new KalahaPit(9 , 6),
                new KalahaPit(10 , 6),
                new KalahaPit(11 , 6),
                new KalahaPit(12 , 6),
                new KalahaPit(13 , 6),
                new KalahaPit(14 , 0));

        BDDAssertions.then(kalahaGame.getPlayerTurn()).isNull();
        BDDAssertions.then(kalahaGame.getPits()).isEqualTo(kalahaPits);
        BDDAssertions.then(kalahaGame.getLeftHouseStones()).isEqualTo(0);
        BDDAssertions.then(kalahaGame.getRightHouseStones()).isEqualTo(0);
    }


    /*
        We first need to run the Mancala Game creation test and use the game id generated to sow the game
     */
    @Test
    public void testManacalaSowPitIndex2 () throws Exception {

        // 1. Run the Mancala Creation Test
        Mockito.when(mancalaClientConfig.getNewMancalaGameUrl()).thenReturn("http://localhost:8080/games");

        WireMock.stubFor(WireMock.post("/games")
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withStatus(HttpStatus.OK.value())
                        .withBody(asJson(mancalaCreate))
                ));

        KalahaGame kalahaGame = mancalaClient.startNewMancalaGame();

        List<KalahaPit> kalahaPits = Arrays.asList(
                new KalahaPit(1 , 6),
                new KalahaPit(2 , 6),
                new KalahaPit(3 , 6),
                new KalahaPit(4 , 6),
                new KalahaPit(5 , 6),
                new KalahaPit(6 , 6),
                new KalahaPit(7 , 0),
                new KalahaPit(8 , 6),
                new KalahaPit(9 , 6),
                new KalahaPit(10 , 6),
                new KalahaPit(11 , 6),
                new KalahaPit(12 , 6),
                new KalahaPit(13 , 6),
                new KalahaPit(14 , 0));

        BDDAssertions.then(kalahaGame.getPlayerTurn()).isNull();
        BDDAssertions.then(kalahaGame.getPits()).isEqualTo(kalahaPits);
        BDDAssertions.then(kalahaGame.getLeftHouseStones()).isEqualTo(0);
        BDDAssertions.then(kalahaGame.getRightHouseStones()).isEqualTo(0);

        // 2. Run the Mancala Sow test for pit 2

        Mockito.when(mancalaClientConfig.getSowMancalaGameUrl(kalahaGame.getId(), 2)).
                thenReturn("http://localhost:8080/games/"+kalahaGame.getId()+"/pits/2");

        WireMock.stubFor(WireMock.put("/games/"+kalahaGame.getId()+"/pits/2")
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withStatus(HttpStatus.OK.value())
                        .withBody(asJson(mancalaSow2))
                ));

        KalahaGame kalahaGameAfterSowingPit2 = mancalaClient.sowMancalaGame(kalahaGame.getId(), 2);

        System.out.println(kalahaGameAfterSowingPit2);

        List<KalahaPit> newKalahaPits = Arrays.asList(
                new KalahaPit(1 , 6),
                new KalahaPit(2 , 0),
                new KalahaPit(3 , 7),
                new KalahaPit(4 , 7),
                new KalahaPit(5 , 7),
                new KalahaPit(6 , 7),
                new KalahaPit(7 , 1),
                new KalahaPit(8 , 7),
                new KalahaPit(9 , 6),
                new KalahaPit(10 , 6),
                new KalahaPit(11 , 6),
                new KalahaPit(12 , 6),
                new KalahaPit(13 , 6),
                new KalahaPit(14 , 0));

        BDDAssertions.then(kalahaGameAfterSowingPit2.getPlayerTurn()).isEqualTo("PlayerB");
        BDDAssertions.then(kalahaGameAfterSowingPit2.getPits()).isEqualTo(newKalahaPits);
        BDDAssertions.then(kalahaGameAfterSowingPit2.getLeftHouseStones()).isEqualTo(0);
        BDDAssertions.then(kalahaGameAfterSowingPit2.getRightHouseStones()).isEqualTo(1);
    }
}
