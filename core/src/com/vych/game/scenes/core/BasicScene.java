package com.vych.game.scenes.core;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.vych.game.SampleGame;
import com.vych.game.managers.gameObjects.GameObjectsManager;
import com.vych.game.renderer.core.SceneRenderer;
import com.vych.game.utils.PropertiesLoader;

import java.lang.reflect.InvocationTargetException;

public abstract class BasicScene implements Screen {
    protected SampleGame game;
    protected OrthographicCamera camera;
    protected SpriteBatch batch;
    protected SceneRenderer renderer;
    protected float[] clearColor = {0, 0, 0, 1};
    protected String name;
    protected boolean disposed;

    public BasicScene(Class<? extends SceneRenderer> rendererClass, String name) {
        this.game = SampleGame.getInstance();
        this.batch = game.getBatch();
        this.name = name;

        try {
            this.renderer = rendererClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        this.renderer.loadAssets();

        PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(
                false,
                propertiesLoader.getInt("game.config.desktop.w"),
                propertiesLoader.getInt("game.config.desktop.h")
        );

        this.disposed = false;
    }

    public SampleGame getGame() {
        return this.game;
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

    public BasicScene setCamera(OrthographicCamera camera) {
        this.camera = camera;
        return this;
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public BasicScene setBatch(SpriteBatch batch) {
        this.batch = batch;
        return this;
    }

    public SceneRenderer getRenderer() {
        return this.renderer;
    }

    public BasicScene setRenderer(SceneRenderer renderer) {
        this.renderer = renderer;
        return this;
    }

    public String getName() {
        return name;
    }

    public boolean isDisposed() {
        return disposed;
    }

    @Override
    public void render(float delta) {
        if (!this.game.isPause()) {
            GameObjectsManager gameObjectsManager = GameObjectsManager.getInstance();

            ScreenUtils.clear(
                    this.clearColor[0],
                    this.clearColor[1],
                    this.clearColor[2],
                    this.clearColor[3]
            );

            this.camera.update();

            this.batch.setProjectionMatrix(this.camera.combined);

            gameObjectsManager.proceedStepInScene(this);

            if (!this.disposed) {
                this.batch.begin();
                this.renderer.renderScene(this.batch);
                this.batch.end();
            }
        }
    }

    @Override
    public void dispose() {
        this.renderer.unloadAssets();
        GameObjectsManager.getInstance().destructByScene(this);
        this.disposed = true;
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        this.camera.setToOrtho(false, width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
