package com.abasc.game;

import com.badlogic.gdx.Game;

public class JeweledGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }

}
