package com.abasc.game.board.rules;

public interface Rule {

    boolean check();

    void apply();

    void refill();
}
