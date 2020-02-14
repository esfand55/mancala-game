package com.dzone.mancala.game.api;

import com.dzone.mancala.game.model.KalahaGame;


public interface KalahaGameApi {
    KalahaGame createGame(int stones);
}
