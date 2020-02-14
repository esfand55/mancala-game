package com.dzone.mancala.game.service;

import com.dzone.mancala.game.model.KalahaGame;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.DirtiesContext;

/*
    These tests are written based on 6 stones Mancala game.
 */

@RunWith(MockitoJUnitRunner.class)
@DirtiesContext
public class MancalaSowingServiceTests {

    private static final Integer defaultPitStones = 6;

    private KalahaGame game;

    private MancalaSowingService mancalaSowingService;

    @Before
    public void setupTest (){
        this.game = new KalahaGame(defaultPitStones);
        this.mancalaSowingService = new MancalaSowingService();
    }

    @Test
    public void testGameCreation () {
        Assert.assertNotNull(this.game);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
    }

    @Test
    public void testSowOfSecondPitPlayerA(){
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = mancalaSowingService.sow(this.game, 2);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");
    }

    @Test
    public void testSowOfSecondPitPlayerB(){
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = mancalaSowingService.sow(this.game, 2);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 9);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");
    }


    @Test
    public void testSowOfSixthPitPlayerA(){
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = mancalaSowingService.sow(this.game, 2);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 9);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        this.game = mancalaSowingService.sow(this.game, 6);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");
    }

    @Test
    public void testWrongTurnByPlayerA(){
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = mancalaSowingService.sow(this.game, 2);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 9);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        this.game = mancalaSowingService.sow(this.game, 6);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        // This is a wrong turn therefore the output remains the same as well as player turn
        this.game = mancalaSowingService.sow(this.game, 1);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");
    }

    @Test
    public void testSowOfSixthPitPlayerB(){
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = mancalaSowingService.sow(this.game, 2);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 9);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        this.game = mancalaSowingService.sow(this.game, 6);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 13);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:8, 2:1, 3:8, 4:8, 5:8, 6:1, 7:2, 8:9, 9:1, 10:8, 11:8, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");
    }

    @Test
    public void testWrongTurnByPlayerB(){
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = mancalaSowingService.sow(this.game, 2);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 9);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        this.game = mancalaSowingService.sow(this.game, 6);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 13);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:8, 2:1, 3:8, 4:8, 5:8, 6:1, 7:2, 8:9, 9:1, 10:8, 11:8, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        // This is a wrong turn therefore the output remains the same as well as player turn
        this.game = mancalaSowingService.sow(this.game, 9);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:8, 2:1, 3:8, 4:8, 5:8, 6:1, 7:2, 8:9, 9:1, 10:8, 11:8, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");
    }

    @Test
    public void testSowOfCollectingOppositePitStoneByPlayerA(){
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = mancalaSowingService.sow(this.game, 2);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 9);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        this.game = mancalaSowingService.sow(this.game, 6);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 13);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:8, 2:1, 3:8, 4:8, 5:8, 6:1, 7:2, 8:9, 9:1, 10:8, 11:8, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        this.game = mancalaSowingService.sow(this.game, 3);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:8, 2:1, 3:0, 4:9, 5:9, 6:2, 7:3, 8:10, 9:2, 10:9, 11:9, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 9);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:8, 2:1, 3:0, 4:9, 5:9, 6:2, 7:3, 8:10, 9:0, 10:10, 11:10, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        this.game = mancalaSowingService.sow(this.game, 2);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:8, 2:0, 3:0, 4:9, 5:9, 6:2, 7:14, 8:10, 9:0, 10:10, 11:0, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");
    }

    @Test
    public void testSowOfCollectingOppositePitStoneByPlayerB(){
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:6, 3:6, 4:6, 5:6, 6:6, 7:0, 8:6, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        this.game = mancalaSowingService.sow(this.game, 2);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:6, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:6, 10:6, 11:6, 12:6, 13:6, 14:0]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 9);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:7, 7:1, 8:7, 9:0, 10:7, 11:7, 12:7, 13:7, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        this.game = mancalaSowingService.sow(this.game, 6);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:7, 2:0, 3:7, 4:7, 5:7, 6:0, 7:2, 8:8, 9:1, 10:8, 11:8, 12:8, 13:8, 14:1]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 13);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:8, 2:1, 3:8, 4:8, 5:8, 6:1, 7:2, 8:9, 9:1, 10:8, 11:8, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        this.game = mancalaSowingService.sow(this.game, 3);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:8, 2:1, 3:0, 4:9, 5:9, 6:2, 7:3, 8:10, 9:2, 10:9, 11:9, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 9);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:8, 2:1, 3:0, 4:9, 5:9, 6:2, 7:3, 8:10, 9:0, 10:10, 11:10, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        this.game = mancalaSowingService.sow(this.game, 2);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:8, 2:0, 3:0, 4:9, 5:9, 6:2, 7:14, 8:10, 9:0, 10:10, 11:0, 12:8, 13:0, 14:2]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 8);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:9, 2:1, 3:1, 4:10, 5:9, 6:2, 7:14, 8:0, 9:1, 10:11, 11:1, 12:9, 13:1, 14:3]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        this.game = mancalaSowingService.sow(this.game, 1);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:0, 2:2, 3:2, 4:11, 5:10, 6:3, 7:15, 8:1, 9:2, 10:12, 11:1, 12:9, 13:1, 14:3]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 9);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:0, 2:2, 3:2, 4:11, 5:10, 6:3, 7:15, 8:1, 9:0, 10:13, 11:2, 12:9, 13:1, 14:3]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");

        this.game = mancalaSowingService.sow(this.game, 2);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:0, 2:0, 3:3, 4:12, 5:10, 6:3, 7:15, 8:1, 9:0, 10:13, 11:2, 12:9, 13:1, 14:3]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("B");

        this.game = mancalaSowingService.sow(this.game, 8);
        Assertions.assertThat(this.game.getPits().toString()).isEqualTo("[1:0, 2:0, 3:3, 4:12, 5:0, 6:3, 7:15, 8:0, 9:0, 10:13, 11:2, 12:9, 13:1, 14:14]");
        Assertions.assertThat(this.game.getPlayerTurn().getTurn()).isEqualTo("A");
    }
}



