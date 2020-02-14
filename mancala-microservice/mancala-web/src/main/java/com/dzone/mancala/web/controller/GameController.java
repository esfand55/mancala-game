/**
 * Copyright 2019 Esfandiyar Talebi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dzone.mancala.web.controller;

import com.dzone.mancala.web.events.SowEvent;
import com.dzone.mancala.web.exceptions.ApiConnectionException;
import com.dzone.mancala.web.client.MancalaClient;
import com.dzone.mancala.web.model.KalahaGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
/*
    This class acts as Controller for the web application
 */
@Component
public class GameController {

    private KalahaGame game;

    @Autowired
    private MancalaClient mancalaClient;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public KalahaGame startNewGame() throws ApiConnectionException {
        try {
            this.game = mancalaClient.startNewMancalaGame();

            return this.game;

        }catch (Exception e){
            throw new ApiConnectionException("Error connecting to Mancala service!");
        }
    }

    public void sow(Integer pitIndex) throws ApiConnectionException {
        try {
            this.game = mancalaClient.sowMancalaGame(this.game.getId(), pitIndex);

            this.eventPublisher.publishEvent(new SowEvent(this, this.game, pitIndex));

        }catch (Exception ex){
            throw new ApiConnectionException("Error connecting to Mancala service!");
        }
    }

    public boolean hasGameStarted () {
        return this.game != null;
    }
}

