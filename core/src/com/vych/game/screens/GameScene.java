package com.vych.game.screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vych.game.SampleGame;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.gameObjects.entities.gameScene.BucketObject;
import com.vych.game.managers.gameObjects.entities.gameScene.DropObject;
import com.vych.game.managers.gameObjects.entities.gameScene.GlobalHUDObject;
import com.vych.game.managers.resources.ResourcesManager;
import com.vych.game.managers.resources.entities.MusicResource;
import com.vych.game.renderer.scenes.GameSceneRenderer;
import com.vych.game.screens.core.BasicScene;

import java.lang.reflect.InvocationTargetException;

public class GameScene extends BasicScene {
    private final Music rainMusic;

    public GameScene(final SampleGame game) {
        super(game, GameSceneRenderer.class);

        clearColor = new float[] {0, 0, 0.2f, 1};

        rainMusic = ResourcesManager.getInstance()
                .getByName("rainMusic", MusicResource.class)
                .getContentCasted();
        rainMusic.setLooping(true);

        GameObjectsManager gameObjectsManager = GameObjectsManager.getInstance();
        try {
            gameObjectsManager.instantiateGameObject(BucketObject.class, renderer, this).setCamera(camera);
            gameObjectsManager.instantiateGameObject(DropObject.class, renderer, this);
            gameObjectsManager.instantiateGameObject(GlobalHUDObject.class, renderer, this);
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
}
