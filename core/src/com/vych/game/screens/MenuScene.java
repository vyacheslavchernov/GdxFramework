package com.vych.game.screens;

import com.vych.game.SampleGame;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.gameObjects.entities.menuScene.MenuGUIObject;
import com.vych.game.renderer.scenes.MenuSceneRenderer;
import com.vych.game.screens.core.BasicScene;

import java.lang.reflect.InvocationTargetException;

public class MenuScene extends BasicScene {

    public MenuScene(SampleGame game) {
        super(game, MenuSceneRenderer.class);

        this.clearColor = new float[]{0, 0.2f, 0, 1};

        GameObjectsManager gameObjectsManager = GameObjectsManager.getInstance();
        try {
            gameObjectsManager.instantiateGameObject(MenuGUIObject.class, this.renderer, this);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
