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

package com.dzone.mancala.web.view;

import com.dzone.mancala.web.controller.GameController;
import com.dzone.mancala.web.model.KalahaGame;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import static com.dzone.mancala.web.constants.KalahaConstants.*;

@SpringComponent
@UIScope
public class PitLayoutComponent extends VerticalLayout implements KeyNotifier {

    private PitComponent pit1 ;
    private PitComponent pit2 ;
    private PitComponent pit3 ;
    private PitComponent pit4 ;
    private PitComponent pit5 ;
    private PitComponent pit6 ;

    private PitComponent pit8 ;
    private PitComponent pit9 ;
    private PitComponent pit10;
    private PitComponent pit11 ;
    private PitComponent pit12 ;
    private PitComponent pit13 ;

    public PitLayoutComponent(GameController gameController) {
        this.pit1 = new PitComponent(firstPitPlayerA, gameController);
        this.pit2 = new PitComponent(secondPitPlayerA, gameController);
        this.pit3 = new PitComponent(thirdPitPlayerA, gameController);
        this.pit4 = new PitComponent(forthPitPlayerA, gameController);
        this.pit5 = new PitComponent(fifthPitPlayerA, gameController);
        this.pit6 = new PitComponent(sixthPitPlayerA, gameController);

        this.pit8 = new PitComponent(firstPitPlayerB, gameController);
        this.pit9 = new PitComponent(secondPitPlayerB, gameController);
        this.pit10 = new PitComponent(thirdPitPlayerB, gameController);
        this.pit11 = new PitComponent(forthPitPlayerB, gameController);
        this.pit12 = new PitComponent(fifthPitPlayerB, gameController);
        this.pit13 = new PitComponent(sixthPitPlayerB, gameController);
        HorizontalLayout playerAPits = new HorizontalLayout(pit1, pit2, pit3, pit4, pit5, pit6);
        HorizontalLayout playerBPits = new HorizontalLayout(pit13, pit12, pit11, pit10, pit9, pit8);

        add(playerBPits, playerAPits);
    }

    public void fillPitStones (KalahaGame game){
        this.pit1.setStones(game.getPitStones(firstPitPlayerA).toString());
        this.pit2.setStones(game.getPitStones(secondPitPlayerA).toString());
        this.pit3.setStones(game.getPitStones(thirdPitPlayerA).toString());
        this.pit4.setStones(game.getPitStones(forthPitPlayerA).toString());
        this.pit5.setStones(game.getPitStones(fifthPitPlayerA).toString());
        this.pit6.setStones(game.getPitStones(sixthPitPlayerA).toString());
        this.pit8.setStones(game.getPitStones(firstPitPlayerB).toString());
        this.pit9.setStones(game.getPitStones(secondPitPlayerB).toString());
        this.pit10.setStones(game.getPitStones(thirdPitPlayerB).toString());
        this.pit11.setStones(game.getPitStones(forthPitPlayerB).toString());
        this.pit12.setStones(game.getPitStones(fifthPitPlayerB).toString());
        this.pit13.setStones(game.getPitStones(sixthPitPlayerB).toString());
    }
}
