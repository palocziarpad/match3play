package com.abasc.game;

import com.badlogic.gdx.ScreenAdapter;

public class AbstractGameScreen extends ScreenAdapter {

    protected final JeweledGame game;

    public AbstractGameScreen(JeweledGame game) {
        this.game = game;
    }
}
