package com.vych.game.scenes;

import com.badlogic.gdx.audio.Music;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.gameObjects.entities.gameScene.BucketObject;
import com.vych.game.managers.gameObjects.entities.gameScene.DropObject;
import com.vych.game.managers.gameObjects.entities.gameScene.GlobalHUDObject;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.MusicResource;
import com.vych.game.renderer.scenes.GameSceneRenderer;
import com.vych.game.scenes.core.BasicScene;
import com.vych.game.utils.Stash;

import java.lang.reflect.InvocationTargetException;

public class GameScene extends BasicScene {
    private final Music rainMusic;

    public GameScene(String name) {
        super(GameSceneRenderer.class, name);

        clearColor = new float[]{0, 0, 0.2f, 1};

        rainMusic = ResourcesManager.getInstance()
                .getByName("rainMusic", MusicResource.class)
                .getContentCasted();
        rainMusic.setLooping(true);

        GameObjectsManager gameObjectsManager = GameObjectsManager.getInstance();
        try {
            gameObjectsManager.instantiateGameObject(BucketObject.class, this).setCamera(camera);
            gameObjectsManager.instantiateGameObject(DropObject.class, this);
            gameObjectsManager.instantiateGameObject(GlobalHUDObject.class, this);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void show() {
        rainMusic.play();
    }

    @Override
    public void pause() {
        rainMusic.pause();
    }

    @Override
    public void resume() {
        rainMusic.play();
    }

    @Override
    public void dispose() {
        rainMusic.stop();
        Stash.remove("rainSpeed");
        super.dispose();
    }
}
