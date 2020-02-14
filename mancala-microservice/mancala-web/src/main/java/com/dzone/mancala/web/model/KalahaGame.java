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

package com.dzone.mancala.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

import static com.dzone.mancala.web.constants.KalahaConstants.*;

@Data
public class KalahaGame {
    private String id;
    private String playerTurn;
    private List<KalahaPit> pits;

    @JsonIgnore
    public Integer getPlayerAStones(){
        return getPit(firstPitPlayerA).getStones() +
                getPit(secondPitPlayerA).getStones() +
                getPit(thirdPitPlayerA).getStones()+
                getPit(forthPitPlayerA).getStones()+
                getPit(fifthPitPlayerA).getStones()+
                getPit(sixthPitPlayerA).getStones();
    }

    @JsonIgnore
    public Integer getPlayerBStones() {
        return getPit(firstPitPlayerB).getStones() +
                getPit(secondPitPlayerB).getStones() +
                getPit(thirdPitPlayerB).getStones()+
                getPit(forthPitPlayerB).getStones()+
                getPit(fifthPitPlayerB).getStones()+
                getPit(sixthPitPlayerB).getStones();
    }

    public KalahaPit getPit (Integer pitIndex){
        return this.pits.stream().filter(p -> p.getId() == pitIndex).findAny().get();
    }

    @JsonIgnore
    public Integer getLeftHouseStones (){
        return getPit(leftPitHouseId).getStones();
    }

    @JsonIgnore
    public Integer getRightHouseStones (){
        return getPit(rightPitHouseId).getStones();
    }

    @JsonIgnore
    public Integer getPitStones (Integer pitIndex){
        return getPit(pitIndex).getStones();
    }
}
