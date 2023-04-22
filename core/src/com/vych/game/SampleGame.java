package com.vych.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vych.game.managers.scenes.SceneManager;
import com.vych.game.scenes.MenuScene;
import com.vych.game.scenes.core.BasicScene;

//TODO: По максимуму заюзать этот объект как синглтон.
// Убрать его из полей других объектов. Все операции с ним сделать через SampleGame.getInstance()
public class SampleGame extends Game {
    private static SampleGame instance;
    private SpriteBatch batch;
    private boolean pause = false;

    public static SampleGame getInstance() {
        if (instance == null) {
            instance = new SampleGame();
        }
        return instance;
    }

    @Override
    public void create() {
        this.batch = new SpriteBatch();

        SceneManager sm = SceneManager.getInstance();
        BasicScene startupScene = sm.loadScene("menu", MenuScene.class);
        sm.setCurrentScene(startupScene);
        this.setScreen(startupScene);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
    }

    @Override
    public void pause() {
        super.pause();
        this.pause = true;
    }

    @Override
    public void resume() {
        super.resume();
        this.pause = false;
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public boolean isPause() {
        return this.pause;
    }
}
