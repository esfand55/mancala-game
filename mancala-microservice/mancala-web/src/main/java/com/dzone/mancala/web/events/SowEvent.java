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

package com.dzone.mancala.web.events;

import com.dzone.mancala.web.model.KalahaGame;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/*
    This event is fired when user clicks on any pit to sow the game. As a result of this event, a call is made to

    Mancala Api and the application is filled with the results of sowing the pit for selected index
 */

@Getter
@Setter
public class SowEvent extends ApplicationEvent {

    private KalahaGame game;
    private Integer pitIndex;
    public SowEvent(Object source, KalahaGame game, Integer pitIndex) {
        super(source);
        this.game = game;
        this.pitIndex = pitIndex;
    }
}
