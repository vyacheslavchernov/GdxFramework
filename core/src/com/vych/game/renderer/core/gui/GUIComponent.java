package com.vych.game.renderer.core.gui;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface GUIComponent {
    String getName();

    void render(Batch batch);

    void update();
}
