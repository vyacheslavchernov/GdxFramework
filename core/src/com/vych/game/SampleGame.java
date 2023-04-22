package com.vych.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vych.game.scenes.MenuScene;

public class SampleGame extends Game {
    private SpriteBatch batch;
    private boolean pause = false;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.setScreen(new MenuScene(this));
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
