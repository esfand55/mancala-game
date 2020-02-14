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

package com.dzone.mancala.game.service;

import com.dzone.mancala.game.api.KalahaGameApi;
import com.dzone.mancala.game.exceptions.ResourceNotFoundException;
import com.dzone.mancala.game.model.KalahaGame;
import com.dzone.mancala.game.repository.KalahaGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
    This class implements data persistence and caching for Kala Games objects
 */

@Service
public class KalahaGameService implements KalahaGameApi {

    @Autowired
    private KalahaGameRepository kalahaGameRepository;

    @Override
    public KalahaGame createGame(int pitStones) {

        KalahaGame kalahaGame = new KalahaGame(pitStones);

        kalahaGameRepository.save(kalahaGame);

        return kalahaGame;
    }

    // loads the game instance from the Cache if game instance was found
    @Cacheable (value = "kalahGames", key = "#id" , unless = "#result  == null")
    public KalahaGame loadGame (String id) throws ResourceNotFoundException {
        Optional<KalahaGame> gameOptional = kalahaGameRepository.findById(id);

        if (!gameOptional.isPresent())
            throw new ResourceNotFoundException("Game id " + id + " not found!");

        return gameOptional.get();
    }

    // put the updated game instance into cache as well as data store
    @CachePut(value = "kalahGames", key = "#kalahaGame.id")
    public KalahaGame updateGame (KalahaGame kalahaGame){
        kalahaGame = kalahaGameRepository.save(kalahaGame);
        return kalahaGame;
    }
}
