package com.vych.game.renderer;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface SceneRenderer {
    void loadAssets();

    void unloadAssets();

    void renderScene(Batch batch);
}
