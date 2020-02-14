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
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@UIScope
@SpringComponent
@Getter
@Slf4j
public class PitComponent extends VerticalLayout {

    private static final Integer defaultPitStones = 0;

    private final TextField pit = new TextField();
    private final Button btn = new Button();

    private GameController gameController;

    public PitComponent() {
        pit.getElement().setAttribute("theme", "align-center");
        pit.setReadOnly(true);
        pit.setValue(defaultPitStones.toString());
        pit.getStyle().set("font-size", "15px");
        pit.getStyle().set("font-weight", "bold");
        pit.setMaxLength(30);
        pit.setMinLength(30);
        btn.getElement().setAttribute("theme", "align-center");
        add(btn, pit);
        setAlignItems(Alignment.CENTER);

        pit.addValueChangeListener(e -> {
            pit.getStyle().set("background-color", "#ff9933");
            new ChangeColorThread(UI.getCurrent(), pit).start();
        });
    }

    private static class ChangeColorThread extends Thread{

        private UI ui;
        private TextField textField;
        public ChangeColorThread(UI ui, TextField textField) {
            this.ui = ui;
            this.textField = textField;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            ui.access(() -> {
                textField.getStyle().set("background-color", "#ffffff");
            });
        }
    }

    public PitComponent(Integer pitIndex, GameController gameController) {
        this();
        this.gameController = gameController;
        pit.setId(pitIndex.toString());

        btn.setText(pitIndex.toString());
        btn.setTabIndex(pitIndex);
        btn.addClickListener(e -> {
            if (!this.gameController.hasGameStarted()){
                Notification.show("Please click on 'Start Game' button to start the game first!");
                return;
            }

            Notification.show(e.getSource().getTabIndex() + " Clicked");
            try {
                this.gameController.sow(e.getSource().getTabIndex());
            } catch (ApiConnectionException ex) {
                log.error(ex.getMessage(), ex);
                Notification.show("Error connecting to the server!. Try later");
            }
        });
    }

    public void setStones(String stones) {
        this.pit.setValue(stones);
    }
}
