package com.dzone.mancala.game.service;

import com.dzone.mancala.game.model.KalahaGame;
import com.dzone.mancala.game.model.KalahaPit;
import com.dzone.mancala.game.repository.KalahaGameRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@RunWith(SpringRunner.class)
public class KalahaGameServiceTests {

    @MockBean
    private KalahaGameRepository kalahaGameRepository;

    @Autowired
    private KalahaGameService kalahaGameService;

    @Test
    public void testCreatingNewlyGameInstance () throws Exception {
        KalahaGame gameInstance = kalahaGameService.createGame(6);

        BDDAssertions.then(gameInstance.getPlayerTurn()).isNull();
        BDDAssertions.then (gameInstance.getPits()).size().isEqualTo(14);
        BDDAssertions.then (gameInstance.getPit(1).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(2).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(3).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(4).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(5).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(6).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(7).getStones()).isEqualTo(0);
        BDDAssertions.then (gameInstance.getPit(8).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(9).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(10).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(11).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(12).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(13).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(14).getStones()).isEqualTo(0);
    }

    @Test
    public void testLoadingGameInstanceFromRepository () throws Exception {

        KalahaGame expectedGame = new KalahaGame("5d414dcd24e4990006e7c9d3" , 6);
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
        expectedGame.setPits(kalahaPits);
        Optional<KalahaGame> gameOptional= Optional.of(expectedGame);
        Mockito.when(kalahaGameRepository.findById("5d414dcd24e4990006e7c9d3")).thenReturn(gameOptional);

        KalahaGame gameInstance = kalahaGameService.loadGame("5d414dcd24e4990006e7c9d3");

        BDDAssertions.then(gameInstance.getId()).isEqualTo("5d414dcd24e4990006e7c9d3");
        BDDAssertions.then(gameInstance.getPlayerTurn()).isNull();
        BDDAssertions.then (gameInstance.getPits()).size().isEqualTo(14);
        BDDAssertions.then (gameInstance.getPit(1).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(2).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(3).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(4).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(5).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(6).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(7).getStones()).isEqualTo(0);
        BDDAssertions.then (gameInstance.getPit(8).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(9).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(10).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(11).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(12).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(13).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(14).getStones()).isEqualTo(0);
    }

    @Test
    public void testUpdatingGameInstanceIntoRepository () throws Exception {

        KalahaGame expectedGame = new KalahaGame("5d414dcd24e4990006e7c9d3" , 6);
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
        expectedGame.setPits(kalahaPits);
        Mockito.when(kalahaGameRepository.save(expectedGame)).thenReturn(expectedGame);

        KalahaGame gameInstance = kalahaGameService.updateGame(expectedGame);

        BDDAssertions.then(gameInstance.getId()).isEqualTo("5d414dcd24e4990006e7c9d3");
        BDDAssertions.then(gameInstance.getPlayerTurn()).isNull();
        BDDAssertions.then (gameInstance.getPits()).size().isEqualTo(14);
        BDDAssertions.then (gameInstance.getPit(1).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(2).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(3).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(4).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(5).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(6).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(7).getStones()).isEqualTo(0);
        BDDAssertions.then (gameInstance.getPit(8).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(9).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(10).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(11).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(12).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(13).getStones()).isEqualTo(6);
        BDDAssertions.then (gameInstance.getPit(14).getStones()).isEqualTo(0);
    }

}
