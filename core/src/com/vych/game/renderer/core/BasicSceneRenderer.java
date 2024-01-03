package com.vych.game.renderer.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.vych.game.context.ComponentsContext;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.gameObjects.entities.core.GameObject;
import com.vych.game.managers.resources.ResourcesManager;

import java.util.List;

public abstract class BasicSceneRenderer implements SceneRenderer {
    protected List<SceneAsset> assets;
    protected ResourcesManager resourcesManager;

    public BasicSceneRenderer() {
        resourcesManager = ComponentsContext.getComponent(ResourcesManager.class);
    }

    @Override
    public void loadAssets() {
        for (SceneAsset asset : assets) {
            resourcesManager.loadResource(asset);
        }
    }

    @Override
    public void loadAssets(SceneAsset asset) {
        assets.add(asset);
        reloadAssets();
    }

    @Override
    public void loadAssets(List<SceneAsset> assetList) {
        assets.addAll(assetList);
        reloadAssets();
    }

    private void reloadAssets() {
        for (SceneAsset asset : assets) {
            if (!resourcesManager.isResourceExist(asset.getResourceName())) {
                resourcesManager.loadResource(asset);
            }
        }
    }

    @Override
    public void unloadAssets() {
        for (SceneAsset asset : assets) {
            resourcesManager.unloadResource(asset);
        }
    }

    @Override
    public void renderScene(Batch batch) {
        List<GameObject> objectsToRender = ComponentsContext
                .getComponent(GameObjectsManager.class)
                .getObjectsByRenderer(this);

        for (GameObject obj : objectsToRender) {
            obj.instanceRender(batch);
        }
        for (GameObject obj : objectsToRender) {
            obj.instanceRenderGUI(batch);
        }
    }
}
