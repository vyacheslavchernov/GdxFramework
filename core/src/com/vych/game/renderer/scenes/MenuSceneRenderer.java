package com.vych.game.renderer.scenes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.gameObjects.entities.core.GameObject;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.core.ResourceType;
import com.vych.game.renderer.core.SceneAsset;
import com.vych.game.renderer.core.SceneRenderer;

import java.util.List;

public class MenuSceneRenderer implements SceneRenderer {
    private final SceneAsset[] assets = {
            new SceneAsset()
                    .setResourceName("defaultFont")
                    .setResourceInternalPath("fonts/font.otf")
                    .setResourceType(ResourceType.FONT),
            new SceneAsset()
                    .setResourceName("buttonIdleImage")
                    .setResourceInternalPath("img/button.bmp")
                    .setResourceType(ResourceType.TEXTURE),
            new SceneAsset()
                    .setResourceName("buttonHoverImage")
                    .setResourceInternalPath("img/button_hover.bmp")
                    .setResourceType(ResourceType.TEXTURE),
            new SceneAsset()
                    .setResourceName("buttonClickImage")
                    .setResourceInternalPath("img/button_click.bmp")
                    .setResourceType(ResourceType.TEXTURE)
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
