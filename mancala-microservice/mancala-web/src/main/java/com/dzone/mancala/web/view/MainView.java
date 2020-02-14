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
import com.dzone.mancala.web.exceptions.ApiConnectionException;
import com.dzone.mancala.web.model.KalahaGame;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Route ("mancala")
@Slf4j
public class MainView extends VerticalLayout {

	private final Button startGameBtn;

	final Label gameIdLabel;
	final TextField gameIdTextField;

	@Autowired
	private GameController gameController;

	final KalahaGameComponent kalahaGameComponent;

	public MainView(KalahaGameComponent kalahaGameComponent, GameController gameController) {
		this.kalahaGameComponent = kalahaGameComponent;
		this.gameController = gameController;

		// build the game information layout
		this.startGameBtn = new Button("Start Game");
		this.gameIdLabel = new Label("Game Id:");
		this.gameIdTextField = new TextField("", "", "");
		this.gameIdTextField.setReadOnly(true);
		this.gameIdTextField.setMinLength(50);

		// build layout for game id
		HorizontalLayout gameIdLayout = new HorizontalLayout(gameIdLabel, gameIdTextField);
		gameIdLayout.setAlignItems(Alignment.CENTER);
		add(gameIdLayout);

		// adding the game itself
		add(kalahaGameComponent);

		// build layout for actions
		HorizontalLayout actions = new HorizontalLayout(startGameBtn);
		add(actions);

        // Instantiate and edit new Customer the new button is clicked
        startGameBtn.addClickListener(e -> {
            try {
                KalahaGame game = this.gameController.startNewGame();
                kalahaGameComponent.newGame(game);
                this.gameIdTextField.setValue(game.getId());
                this.kalahaGameComponent.getPlayerTurnTextField().setValue("");

                Notification.show("New Game started. id:" + game.getId(), 3000, Notification.Position.MIDDLE);

            } catch (ApiConnectionException ex) {
                Notification.show("Error!. Message:" + ex.getMessage());
                log.error(ex.getMessage(), ex);
            }
        });
	}
}
