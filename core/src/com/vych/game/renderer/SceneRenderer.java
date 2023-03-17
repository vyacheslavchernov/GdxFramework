package com.vych.game.renderer;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface SceneRenderer {
    public void loadAssets();

    public void unloadAssets();

    public void renderScene(Batch batch);
}
