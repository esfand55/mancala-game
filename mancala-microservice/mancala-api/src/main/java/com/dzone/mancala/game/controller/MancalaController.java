/**
 * Copyright 2019 Esfandiyar Talebi
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dzone.mancala.game.controller;

import com.dzone.mancala.game.constants.KalahaConstants;
import com.dzone.mancala.game.exceptions.MancalaApiException;
import com.dzone.mancala.game.model.KalahaGame;
import com.dzone.mancala.game.service.KalahaGameService;
import com.dzone.mancala.game.service.MancalaSowingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/games")
@Api(value = "Mancala game API. Set of endpoints for Creating and Sowing the Game")
public class MancalaController {

    @Autowired
    private KalahaGameService mancalaGameService;

    @Autowired
    private MancalaSowingService mancalaSowingService;

    @Value("${mancala.pit.stones}")
    private Integer pitStones;

    @PostMapping
    @ApiOperation(value = "Endpoint for creating new Mancala game instance. It returns a KalahaGame object with unique GameId used for sowing the game",
            produces = "Application/JSON", response = KalahaGame.class, httpMethod = "POST")
    public ResponseEntity<KalahaGame> createGame() throws Exception {

        log.info("Invoking create() endpoint... ");

        KalahaGame game = mancalaGameService.createGame(pitStones);

        log.info("Game instance created. Id=" + game.getId());

        log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(game));

        mancalaGameService.updateGame(game);

        return ResponseEntity.ok(game);
    }

    @PutMapping(value = "{gameId}/pits/{pitId}")
    @ApiOperation(value = "Endpoint for sowing the game. It keeps the history of the Game instance for consecutive requests. ",
            produces = "Application/JSON", response = KalahaGame.class, httpMethod = "PUT")
    public ResponseEntity<KalahaGame> sowGame(
            @ApiParam(value = "The id of game created by calling createGame() method. It can't be empty or null", required = true)
            @PathVariable(value = "gameId") String gameId,
            @PathVariable(value = "pitId") Integer pitId) throws Exception {

        log.info("Invoking sow() endpoint. GameId: " + gameId + "  , pit Index: " + pitId);

        if (pitId == null || pitId < 1 || pitId >= KalahaConstants.leftPitHouseId || pitId == KalahaConstants.rightPitHouseId)
            throw new MancalaApiException("Invalid pit Index!. It should be between 1..6 or 8..13");

        KalahaGame kalahaGame = mancalaGameService.loadGame(gameId);

        kalahaGame = mancalaSowingService.sow(kalahaGame, pitId);

        mancalaGameService.updateGame(kalahaGame);

        log.info("sow is called for Game id:" + gameId + " , pitIndex:" + pitId);

        log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(kalahaGame));

        return ResponseEntity.ok(kalahaGame);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Endpoint for returning the latest status of the Game",
            produces = "Application/JSON", response = KalahaGame.class, httpMethod = "GET")
    public ResponseEntity<KalahaGame> gameStatus(
            @ApiParam(value = "The id of game created by calling createGame() method. It's an String e.g. 5d34968590fcbd35b086bc21. It can't be empty or null",
                    required = true)
            @PathVariable(value = "id") String gameId) throws Exception {

        return ResponseEntity.ok(mancalaGameService.loadGame(gameId));
    }


}
