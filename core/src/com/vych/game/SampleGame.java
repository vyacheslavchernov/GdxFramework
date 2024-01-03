package com.vych.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vych.game.context.ComponentsContext;
import com.vych.game.managers.scenes.SceneManager;
import com.vych.game.scenes.core.BasicScene;

//TODO: По максимуму заюзать этот объект как синглтон.
// Убрать его из полей других объектов. Все операции с ним сделать через SampleGame.getInstance()
//
//TODO: Класс сейчас(не знаю как будет дальше) не используется ни для чего, кроме переключения сцен и инициализации приложения
// Может сделать его вместо синглтона полем SceneManager?
//
//TODO: Это единственный синглтон в проекте. Его следует убрать
public class SampleGame extends Game {
    private static SampleGame instance;
    private static Class<? extends BasicScene> startupScene;
    private SpriteBatch batch;
    private boolean pause = false;

    public static SampleGame getInstance() {
        if (instance == null) {
            if (startupScene == null) {
                throw new RuntimeException("Попытка инициализировать объект игры без установки начальной сцены");
            }
            instance = new SampleGame();
        }
        return instance;
    }

    public static void setStartupScene(Class<? extends BasicScene> startupScene) {
        SampleGame.startupScene = startupScene;
    }

    @Override
    public void create() {
        this.batch = new SpriteBatch();

        SceneManager sm = ComponentsContext.getComponent(SceneManager.class);
        BasicScene startupScene = sm.loadScene("startup", SampleGame.startupScene);
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
