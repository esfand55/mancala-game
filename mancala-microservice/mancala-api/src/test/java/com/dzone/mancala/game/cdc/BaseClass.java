package com.dzone.mancala.game.cdc;

import com.dzone.mancala.game.controller.MancalaController;
import com.dzone.mancala.game.model.KalahaGame;
import com.dzone.mancala.game.model.KalahaPit;
import com.dzone.mancala.game.model.PlayerTurns;
import com.dzone.mancala.game.service.KalahaGameService;
import com.dzone.mancala.game.service.MancalaSowingService;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


/*
    This is used for Spring Contact Testing.  It prepares the Stub dependencies
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server.port=0")
@RunWith(SpringRunner.class)
@Import({MancalaController.class, KalahaGameService.class})
@DirtiesContext
public class BaseClass {

    @LocalServerPort
    private String port;

    @MockBean
    private KalahaGameService mancalaGameService;

    @MockBean
    private MancalaSowingService mancalaSowingService;

    @Autowired
    private MancalaController mancalaController;

    @Before
    public void setupGame() throws Exception {
        RestAssured.baseURI = "http://localhost:" + this.port;

        KalahaGame expectedGame = new KalahaGame("5d414dcd24e4990006e7c900", 6);

        Mockito.when(this.mancalaGameService.createGame(6))
                .thenReturn(expectedGame);

        Mockito.when(mancalaGameService.loadGame("5d414dcd24e4990006e7c900"))
                .thenReturn(expectedGame);

        List<KalahaPit> pitsAfterSowingIndex2 = Arrays.asList(
                new KalahaPit(1, 6),
                new KalahaPit(2, 0),
                new KalahaPit(3, 7),
                new KalahaPit(4, 7),
                new KalahaPit(5, 7),
                new KalahaPit(6, 7),
                new KalahaPit(7, 1),
                new KalahaPit(8, 7),
                new KalahaPit(9, 6),
                new KalahaPit(10, 6),
                new KalahaPit(11, 6),
                new KalahaPit(12, 6),
                new KalahaPit(13, 6),
                new KalahaPit(14, 0));

        KalahaGame gameAfterSowingIndex2 = new KalahaGame("5d414dcd24e4990006e7c900", 6);
        gameAfterSowingIndex2.setPits(pitsAfterSowingIndex2);
        gameAfterSowingIndex2.setPlayerTurn(PlayerTurns.PlayerB);

        Mockito.when(this.mancalaSowingService.sow(expectedGame, 2))
                .thenReturn(gameAfterSowingIndex2);

        RestAssuredMockMvc.standaloneSetup(mancalaController);
    }
}
