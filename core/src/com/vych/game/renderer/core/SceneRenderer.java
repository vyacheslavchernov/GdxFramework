package com.vych.game.renderer.core;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.List;

public interface SceneRenderer {
    void loadAssets();

    void loadAssets(SceneAsset asset);

    void loadAssets(List<SceneAsset> assetList);

    void unloadAssets();

    void renderScene(Batch batch);
}
