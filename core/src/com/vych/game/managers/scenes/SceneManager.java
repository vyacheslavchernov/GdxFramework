package com.vych.game.managers.scenes;

import com.vych.game.SampleGame;
import com.vych.game.managers.scenes.exceptions.IllegalSceneName;
import com.vych.game.managers.scenes.exceptions.UnloadedSceneUse;
import com.vych.game.scenes.core.BasicScene;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static SceneManager instance = new SceneManager();

    private Map<String, BasicScene> scenes;

    private BasicScene currentScene;

    public SceneManager() {
        this.scenes = new HashMap<>();

    }

    public static SceneManager getInstance() {
        return instance;
    }

    public BasicScene getCurrentScene() {
        return currentScene;
    }

    public SceneManager setCurrentScene(BasicScene currentScene) {
        this.currentScene = currentScene;
        return this;
    }

    public BasicScene loadScene(String sceneName, Class<? extends BasicScene> sceneClass) {
        if (this.scenes.containsKey(sceneName)) {
            throw new IllegalSceneName(String.format("Сцена с именем %s уже существует", sceneName));
        }

        BasicScene scene;
        try {
            scene = sceneClass.getDeclaredConstructor(String.class).newInstance(sceneName);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        scenes.put(sceneName, scene);
        return scene;
    }

    public void unloadScene(String sceneName) {
        this.scenes.get(sceneName).dispose();
        this.scenes.remove(sceneName);
    }

    public void unloadScene(BasicScene scene) {
        unloadScene(scene.getName());
    }

    public void switchScene(String sceneName, boolean unloadCurrent) {
        if (!this.scenes.containsKey(sceneName)) {
            throw new UnloadedSceneUse(
                    String.format(
                            "Попытка переключиться на сцену с именем %s, которая ещё не была загружена",
                            sceneName
                    )
            );
        }

        BasicScene nextScene = this.scenes.get(sceneName);
        SampleGame.getInstance().setScreen(nextScene);

        if (unloadCurrent) {
            unloadScene(this.currentScene);
            nextScene.getRenderer().loadAssets();
        }

        this.currentScene = nextScene;
    }

    public void switchScene(BasicScene scene, boolean unloadCurrent) {
        switchScene(scene.getName(), unloadCurrent);
    }

    public BasicScene getScene(String sceneName) {
        return this.scenes.get(sceneName);
    }
}
