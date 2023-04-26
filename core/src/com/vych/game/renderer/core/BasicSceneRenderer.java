package com.vych.game.renderer.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.gameObjects.entities.core.GameObject;
import com.vych.game.managers.resources.ResourcesManager;

import java.util.List;

public abstract class BasicSceneRenderer implements SceneRenderer {

    protected void loadAssets(SceneAsset[] assets) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        for (SceneAsset asset : assets) {
            resourcesManager.loadResource(asset);
        }
    }

    protected void unloadAssets(SceneAsset[] assets) {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        for (SceneAsset asset : assets) {
            resourcesManager.unloadResource(asset);
        }
    }

    @Override
    public void renderScene(Batch batch) {
        List<GameObject> objectsToRender = GameObjectsManager.getInstance().getObjectsByRenderer(this);
        for (GameObject obj : objectsToRender) {
            obj.instanceRender(batch);
        }
        for (GameObject obj : objectsToRender) {
            obj.instanceRenderGUI(batch);
        }
    }
}
