package com.dzone.mancala.game.api;

import com.dzone.mancala.game.model.KalahaGame;

public interface KalahaSowingApi {
    KalahaGame sow (KalahaGame game, int pitIndex);
}
