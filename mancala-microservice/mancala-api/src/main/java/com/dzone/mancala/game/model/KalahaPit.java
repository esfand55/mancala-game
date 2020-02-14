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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KalahaPit implements Serializable {

    private Integer id;
    private Integer stones;

    @JsonIgnore
    public Boolean isEmpty (){
        return this.stones == 0;
    }

    public void clear (){
        this.stones = 0;
    }

    public void sow () {
        this.stones++;
    }

    public void addStones (Integer stones){
        this.stones+= stones;
    }

    @Override
    public String toString() {
        return  id.toString() +
                ":" +
                stones.toString() ;
    }
}
