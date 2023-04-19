package com.vych.game.renderer.scenes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.gameObjects.entities.core.GameObject;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.core.ResourceType;
import com.vych.game.renderer.core.SceneAsset;
import com.vych.game.renderer.core.SceneRenderer;

import java.util.List;

public class GameSceneRenderer implements SceneRenderer {
    private final SceneAsset[] assets = {
            new SceneAsset()
                    .setResourceName("dropImage")
                    .setResourceInternalPath("droplet.png")
                    .setResourceType(ResourceType.TEXTURE),

            new SceneAsset()
                    .setResourceName("bucketImage")
                    .setResourceInternalPath("bucket.png")
                    .setResourceType(ResourceType.TEXTURE),

            new SceneAsset()
                    .setResourceName("dropSound")
                    .setResourceInternalPath("drop.wav")
                    .setResourceType(ResourceType.SOUND),

            new SceneAsset()
                    .setResourceName("rainMusic")
                    .setResourceInternalPath("rain.mp3")
                    .setResourceType(ResourceType.MUSIC),

            new SceneAsset()
                    .setResourceName("defaultFont")
                    .setResourceInternalPath("font.otf")
                    .setResourceType(ResourceType.FONT)
    };

    @Override
    public void loadAssets() {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        for (SceneAsset asset : this.assets) {
            resourcesManager.loadResource(asset);
        }
    }

    @Override
    public void unloadAssets() {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        for (SceneAsset asset : this.assets) {
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
