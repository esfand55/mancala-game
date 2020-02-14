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

package com.dzone.mancala.game.model;

import com.dzone.mancala.game.exceptions.MancalaApiException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static com.dzone.mancala.game.constants.KalahaConstants.*;

@Document(collection = "games")
@Setter
@Getter
public class KalahaGame implements Serializable{

    @Id
    private String id;

    private List<KalahaPit> pits;

    private PlayerTurns playerTurn;

    @JsonIgnore
    private int currentPitIndex;

    public KalahaGame() {
        this (defaultPitStones);
    }

    public KalahaGame(int pitStones) {
        this.pits = Arrays.asList(
                new KalahaPit(firstPitPlayerA, pitStones),
                new KalahaPit(secondPitPlayerA, pitStones),
                new KalahaPit(thirdPitPlayerA, pitStones),
                new KalahaPit(forthPitPlayerA, pitStones),
                new KalahaPit(fifthPitPlayerA, pitStones),
                new KalahaPit(sixthPitPlayerA, pitStones),
                new KalahaHouse(rightPitHouseId),
                new KalahaPit(firstPitPlayerB, pitStones),
                new KalahaPit(secondPitPlayerB, pitStones),
                new KalahaPit(thirdPitPlayerB, pitStones),
                new KalahaPit(forthPitPlayerB, pitStones),
                new KalahaPit(fifthPitPlayerB, pitStones),
                new KalahaPit(sixthPitPlayerB, pitStones),
                new KalahaHouse(leftPitHouseId));
    }

    public KalahaGame(String id, Integer pitStones) {
        this (pitStones);
        this.id = id;
    }

    // returns the corresponding pit of particular index
    public KalahaPit getPit(Integer pitIndex) throws MancalaApiException {
        try {
            return this.pits.get(pitIndex-1);
        }catch (Exception e){
            throw  new MancalaApiException("Invalid pitIndex:"+ pitIndex +" has given!");
        }
    }

    @Override
    public String toString() {
        return "KalahaGame{" +
                ", pits=" + pits +
                ", playerTurn=" + playerTurn +
                '}';
    }
}
