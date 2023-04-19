package com.vych.game.renderer;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.gameObjects.entities.core.GameObject;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.MusicResource;
import com.vych.game.managers.resources.entities.core.ResourceType;
import com.vych.game.managers.resources.exceptions.CannotLoadResource;
import com.vych.game.managers.resources.exceptions.CannotUnloadResource;

import java.util.List;

public class GameScreenRenderer implements SceneRenderer {
    @Override
    public void loadAssets() {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        try {
            resourcesManager.loadResource("dropImage", "droplet.png", ResourceType.TEXTURE);
            resourcesManager.loadResource("bucketImage", "bucket.png", ResourceType.TEXTURE);
            resourcesManager.loadResource("dropSound", "drop.wav", ResourceType.SOUND);
            resourcesManager.loadResource("rainMusic", "rain.mp3", ResourceType.MUSIC);
        } catch (CannotLoadResource e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unloadAssets() {
        ResourcesManager resourcesManager = ResourcesManager.getInstance();
        try {
            resourcesManager.unloadResource("dropImage");
            resourcesManager.unloadResource("bucketImage");
            resourcesManager.unloadResource("dropSound");
            resourcesManager.unloadResource("rainMusic");
        } catch (CannotUnloadResource e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void renderScene(Batch batch) {
        List<GameObject> objectsToRender = GameObjectsManager.getInstance().getObjectsByRenderer(this);
        for (GameObject obj : objectsToRender) {
            obj.instanceRender(batch);
        }
    }
}
