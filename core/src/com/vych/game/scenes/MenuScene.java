package com.vych.game.scenes;

import com.vych.game.context.ComponentsContext;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.managers.gameObjects.entities.menuScene.MenuGUIObject;
import com.vych.game.renderer.scenes.MenuSceneRenderer;
import com.vych.game.scenes.core.BasicScene;

import java.lang.reflect.InvocationTargetException;

public class MenuScene extends BasicScene {

    public MenuScene(String name) {
        super(MenuSceneRenderer.class, name);

        this.clearColor = new float[]{0, 0.2f, 0, 1};

        GameObjectsManager gameObjectsManager = ComponentsContext.getComponent(GameObjectsManager.class);
        try {
            gameObjectsManager.instantiateGameObject(MenuGUIObject.class, this);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
