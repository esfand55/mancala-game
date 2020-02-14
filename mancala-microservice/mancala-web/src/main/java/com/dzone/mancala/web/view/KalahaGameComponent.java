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
import com.dzone.mancala.web.events.SowEvent;
import com.dzone.mancala.web.model.KalahaGame;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@UIScope
@SpringComponent
@Slf4j
@Component
public class KalahaGameComponent extends VerticalLayout implements KeyNotifier {

    private final PitLayoutComponent pitLayoutComponent;

    private final PitComponent rightHouse ;
    private final PitComponent leftHouse ;

    final Label playerTurnLabel;
    final TextField playerTurnTextField;

    final Label winLabel;


    public KalahaGameComponent(PitLayoutComponent pitLayoutComponent, @Autowired GameController gameController) {
        this.pitLayoutComponent = pitLayoutComponent;

        this.playerTurnLabel = new Label("Player turn:");
        this.playerTurnTextField = new TextField("");
        this.playerTurnTextField.setReadOnly(true);

        // build layout for game information
        HorizontalLayout turnLayout = new HorizontalLayout(playerTurnLabel, playerTurnTextField);
        turnLayout.setAlignItems(Alignment.CENTER);
        add(turnLayout);

        rightHouse = new PitComponent(7 , gameController);
        rightHouse.setAlignItems(Alignment.CENTER);
        rightHouse.add(new Label("Player A"));

        leftHouse = new PitComponent(14, gameController);
        leftHouse.setAlignItems(Alignment.CENTER);
        leftHouse.add(new Label("Player B"));

        HorizontalLayout gameLayout = new HorizontalLayout(leftHouse, pitLayoutComponent, rightHouse);
        gameLayout.setAlignItems(Alignment.CENTER);

        add(gameLayout);

        // Adding the win layout
        winLabel = new Label("");
        winLabel.setVisible(false);
        winLabel.getStyle().set("font-size", "50px");
        winLabel.getStyle().set("color", "#ff0000");

        HorizontalLayout winLayout = new HorizontalLayout(winLabel);
        winLayout.setAlignItems(Alignment.CENTER);

        add(winLayout);

        setAlignItems(Alignment.CENTER);
    }

    public TextField getPlayerTurnTextField() {
        return playerTurnTextField;
    }

    public void fillMancala(KalahaGame game) {
        this.leftHouse.setStones(game.getLeftHouseStones().toString());
        this.rightHouse.setStones(game.getRightHouseStones().toString());
        this.pitLayoutComponent.fillPitStones(game);
    }

    public void newGame(KalahaGame game) {
        this.fillMancala(game);
        this.winLabel.setVisible(false);
    }

    @EventListener
    public void handleFlushEvent (SowEvent event) {
        KalahaGame game = event.getGame();
        this.fillMancala(game);
        this.playerTurnTextField.setValue(event.getGame().getPlayerTurn());

        Integer playerARemainingStones = game.getPlayerAStones();
        Integer playerBRemainingStones = game.getPlayerBStones();

        if (playerARemainingStones == 0 || playerBRemainingStones ==0){
            Integer totalA = playerARemainingStones + game.getRightHouseStones();
            Integer totalB = playerBRemainingStones + game.getLeftHouseStones();

            this.leftHouse.setStones(totalB.toString());
            this.rightHouse.setStones(totalA.toString());

            if (totalA > totalB)
                this.winLabel.setText("Game Over!. Player A Won!!!");
            else
                this.winLabel.setText("Game Over!. Player B Won!!!");

            this.winLabel.setVisible(true);
        }
    }
}
